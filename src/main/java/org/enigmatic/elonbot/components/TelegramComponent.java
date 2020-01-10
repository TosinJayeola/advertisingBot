package org.enigmatic.elonbot.components;

import com.google.inject.internal.cglib.core.$ClassEmitter;
import org.enigmatic.elonbot.model.CommandModel;
import org.enigmatic.elonbot.model.ContactModel;
import org.enigmatic.elonbot.services.CommandDetailsRepository;
import org.enigmatic.elonbot.services.ContactDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.text.MessageFormat;
import java.util.*;

@Component
public class TelegramComponent extends TelegramLongPollingBot {
    Optional<ContactModel> contactModel;
    List<CommandModel> commandModelList;
    @Autowired
    ContactDetailsRepository contactDetailsRepository;
    @Autowired
    CommandDetailsRepository commandDetailsRepository;

    private Message message;
    private SendMessage sendMessage;
    private EditMessageText editMessageText;
    private CallbackQuery callbackQuery;
    private Long chatId;
    private Integer messageId;
    private String inlineMessageId;

    @Override
    public void onUpdateReceived(Update update) {
//        List<PhotoSize> photos = update.getMessage().getPhoto();
//        // Know file_id
//        String f_id = photos.stream()
//                .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
//                .findFirst()
//                .orElse(null).getFileId();
//        System.out.println(f_id);
//        AgADAgAD8KsxG6J3-EnC2UzkIQzDuVfptw8ABAEAAwIAA3kAA8FRBgABFgQ
//        SendPhoto sendPhoto = new SendPhoto(); sendPhoto.setChatId(update.getMessage().getChatId()).setPhoto("AgADAgAD8KsxG6J3-EnC2UzkIQzDuVfptw8ABAEAAwIAA3kAA8FRBgABFgQ");
//        try {
//            execute(sendPhoto);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
        if (update.hasMessage()) {
            System.out.println("fesfes");
            message = update.getMessage();
            chatId = message.getChatId();
            sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            contactModel = contactDetailsRepository.findByChatId(message.getChatId());

            if (contactModel.isPresent()) {
                setMenuButtons();
                doActions();
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else {
                if (message.hasContact()) {
                    contactDetailsRepository.save(new ContactModel(message.getChatId(),
                            message.getContact().getUserID(),
                            message.getContact().getFirstName(),
                            message.getContact().getPhoneNumber(),
                            0));
                    setMenuButtons();
                    sendMessage.setText("Welcome " + message.getContact().getFirstName() + "!");
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else {
                    sendMessage.setText("Please send your phone number");
                    KeyboardButton button = new KeyboardButton();
                    button.setText("Send phone number");
                    button.setRequestContact(true);
                    KeyboardRow row = new KeyboardRow();
                    row.add(button);

                    ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
                    markup.setOneTimeKeyboard(true).setResizeKeyboard(true);
                    markup.setKeyboard(Arrays.asList(row));
                    sendMessage.setReplyMarkup(markup);

                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
        }
    }

    private void doActions() {
        System.out.println(message.getText());
        switch (message.getText()) {
            case "To main":
                setMenuButtons();
                break;
            case "\uD83D\uDCB0 Balance":
                sendMessage.setText(MessageFormat.format(
                        "Количество  ПЛАТНЫХ  объявлений: {0}  \n" +
                                "Количество  БЕСПЛАТНЫХ  объявлений: {1}  \n" +
                                "Ваш ID номер: {3} \n" +
                                "\n" +
                                "Вы можете пополнить свой счет в любом месте системы CLICK, PAYME или PAYNET",
                        contactModel.get().getBalance(),
                        contactModel.get().getFreeBalance(),
                        contactModel.get().getUserId()
                ));
                //  message.getPhoto();

                KeyboardRow row1 = new KeyboardRow();
                KeyboardRow row2 = new KeyboardRow();
                row1.add((new KeyboardButton().setText("\uD83D\uDCB5\uD83D\uDCB3 Account replenishment")));
                row2.add(new KeyboardButton().setText("\uD83C\uDFE0 To main"));
                sendMessage.setReplyMarkup(new ReplyKeyboardMarkup().setKeyboard(Arrays.asList(row1, row2)).setResizeKeyboard(true));
                break;
            default:
                this.sendMessage.setText("Command not found!");
                break;
        }
    }

    private void setMenuButtons() {
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardRow row3 = new KeyboardRow();

        row1.add(new KeyboardButton().setText("Sell"));
        row1.add(new KeyboardButton().setText("Buy"));

        row2.add(new KeyboardButton().setText("\uD83D\uDCCC My adds"));
        row2.add(new KeyboardButton().setText("\uD83D\uDCB0 Balance"));

        row3.add(new KeyboardButton().setText("⚙️Settings"));
        row3.add(new KeyboardButton().setText("\uD83C\uDD98 SOS"));
        sendMessage.setReplyMarkup(
                new ReplyKeyboardMarkup()
                        .setKeyboard(Arrays.asList(row1, row2, row3))
                        .setResizeKeyboard(true));
    }

    @Override
    public String getBotUsername() {
        return "E'lonBot";
    }

    @Override
    public String getBotToken() {
        return "937610028:AAFbHtvs4HdzIPbiWDXvtMDGTp9lYcGZwzc";
    }
}