package iuliia.quotesbot.quotes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Quote {
    @Id
    @GeneratedValue
    private long id;
    @NotEmpty
    private String content;
    @NotEmpty
    private String source;

}
