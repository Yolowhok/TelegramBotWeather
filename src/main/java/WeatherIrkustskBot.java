import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileNotFoundException;
import java.io.IOException;

public class WeatherIrkustskBot extends TelegramLongPollingBot {

    public WeatherIrkustskBot() throws IOException {
    }

    @Override
    public String getBotUsername() {
        return "WeatherIrkustskBot";
    }

    @Override
    public String getBotToken() {
        return "5328814181:AAFYXRCFAsxjdgQtJfB6Wlpc1EePRnobM2A";
    }

    Parsing da = new Parsing();

    @Override
    public void onUpdateReceived(Update update)  {
        SendMessage message = new SendMessage();
        if (update.getMessage().getText().equals("/start")) {
            message.setChatId(update.getMessage().getChatId().toString());
            message.setText("Введите город");
            try {
                execute(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else if (update.hasMessage() && update.getMessage().hasText()) {
            // Create a SendMessage object with mandatory fields
            message.setChatId(update.getMessage().getChatId().toString()); //присваиваю сообщению ID полльзователя
            try {
                String pogoda = da.WEather(update.getMessage().getText().toLowerCase());
                if (pogoda==null) {
                    message.setText("Неверный город");
                }
                else {
                    message.setText(pogoda);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(update.getMessage().getFrom() + "\n СООБЩЕНИЕ:   " + update.getMessage().getText() + "\n");
            try {
                execute(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
