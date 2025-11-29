package com.nishjournal.Nishjournal.entry;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Data
@Document(collection = "config_journal_app")
@NoArgsConstructor
public class ConfigJournalAppEntity {

    private String key;
    private String value;


}
