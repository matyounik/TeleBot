package academy.prog.model;

import jakarta.persistence.*;

@Entity
@Table(name = "bot_message")
public class BotMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message_key")
    private String key;

    private String lang;
    private String text;

    public BotMessage() {
    }

    public BotMessage(Long id, String key, String lang, String text) {
        this.id = id;
        this.key = key;
        this.lang = lang;
        this.text = text;
    }

}
