package it.p0gram3r.playground;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;

import java.io.File;

@Builder(access = AccessLevel.PUBLIC)
@Data
public class Message {
    private String sender;
    private String recipient;
    private String text;
    private File file;

    public static class MessageBuilder {
        private String text;
        private File file;

        public MessageBuilder text(String text) {
            this.text = text;
            verifyTextOrFile();
            return this;
        }

        public MessageBuilder file(File file) {
            this.file = file;
            verifyTextOrFile();
            return this;
        }

        private void verifyTextOrFile() {
            if (text != null && file != null) {
                throw new IllegalStateException("Cannot send 'text' and 'file'.");
            }
        }
    }
}