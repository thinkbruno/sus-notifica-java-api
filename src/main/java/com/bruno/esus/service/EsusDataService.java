package com.bruno.esus.service;

import com.bruno.esus.client.SusClient;
import com.bruno.esus.model.EsusRecord;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import java.io.StringReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class EsusDataService {

    private final SusClient susClient;

    private final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public EsusDataService(SusClient susClient) {
        this.susClient = susClient;
    }

    public List<EsusRecord> getProcessedRecords(int limit) {
        List<EsusRecord> records = new ArrayList<>();

        // Coleta o stream do CSV público
        String csvData = susClient.downloadDataStream().blockFirst();

        if (csvData == null || csvData.isEmpty()) {
            return records;
        }

        // Parser utilizando o Builder
        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setDelimiter(';') // Padrão brasileiro para CSV/Excel
                .setHeader()
                .setSkipHeaderRecord(true)
                .setIgnoreHeaderCase(true)
                .setTrim(true)
                .build();

        try (StringReader reader = new StringReader(csvData);
             CSVParser csvParser = new CSVParser(reader, format)) {

            for (CSVRecord csvRecord : csvParser) {
                if (records.size() >= limit) break;

                // verifica se a coluna existe antes de acessar
                String id = getSafeValue(csvRecord, "id");
                String municipio = getSafeValue(csvRecord, "municipio");
                String estado = getSafeValue(csvRecord, "estado");
                String dataRaw = getSafeValue(csvRecord, "dataNotificacao");
                String resultado = getSafeValue(csvRecord, "resultadoTeste");

                records.add(new EsusRecord(
                        id,
                        municipio,
                        estado,
                        formatarData(dataRaw),
                        resultado,
                        true // registroAtual: sempre true para dados consolidados
                ));
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao processar dados abertos: " + e.getMessage());
        }
        return records;
    }

    private String getSafeValue(CSVRecord record, String column) {
        return record.isMapped(column) ? record.get(column) : "N/A";
    }

    private String formatarData(String dataRaw) {
        try {
            // Normaliza para o padrão ISO (yyyy-MM-dd)
            return LocalDate.parse(dataRaw, inputFormatter).toString();
        } catch (Exception e) {
            return dataRaw;
        }
    }
}