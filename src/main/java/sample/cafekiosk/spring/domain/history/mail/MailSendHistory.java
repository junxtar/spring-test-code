package sample.cafekiosk.spring.domain.history.mail;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sample.cafekiosk.spring.global.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class MailSendHistory extends BaseEntity {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long id;

   private String toEmail;
   private String fromEmail;
   private String subject;
   private String content;

   @Builder
   public MailSendHistory(String toEmail, String fromEmail, String subject, String content) {
      this.toEmail = toEmail;
      this.fromEmail = fromEmail;
      this.subject = subject;
      this.content = content;
   }
}

