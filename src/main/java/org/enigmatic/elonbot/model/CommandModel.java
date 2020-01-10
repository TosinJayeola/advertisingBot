package org.enigmatic.elonbot.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "commands")
public class CommandModel implements Serializable{

    @EmbeddedId
    private CommandPK commandPK;

    @Column
    private String command;

    @Embeddable
    public static class CommandPK implements Serializable {
        @Column(name = "chat_id")
        protected Long chatId;
        protected Integer level;

        public CommandPK() {
        }

        public CommandPK(Long chatId, Integer level) {
            this.chatId = chatId;
            this.level = level;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CommandPK commandPK = (CommandPK) o;
            return Objects.equals(chatId, commandPK.chatId) &&
                    Objects.equals(level, commandPK.level);
        }

        @Override
        public int hashCode() {
            return Objects.hash(chatId, level);
        }
    }
    public CommandModel() {
    }

    public CommandModel(Long chatid, Integer level, String command) {
        this.commandPK = new CommandPK(chatid, level);
        this.command = command;
    }

    public CommandPK getCommandPK() {
        return commandPK;
    }

    public void setCommandPK(CommandPK commandPK) {
        this.commandPK = commandPK;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}