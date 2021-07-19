package com.tgbot.TgBot.Telegram;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.tgbot.TgBot.Services.EntitiesService;
import com.tgbot.TgBot.Services.TelegramService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class TelegramBotConnection {
    private class TelegramUpdatesListener implements UpdatesListener {
        @Override
        public int process(List<Update> updates) {
            for (Update update : updates) {
                processUpdate(update);
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        }

        private void processUpdate(Update update) {
            if (update.callbackQuery() != null) {
                telegramService.getCallBack(bot,update);

            } else {
                telegramService.getMessage(bot,update);
            }
        }
    }

    private final EntitiesService entitiesService;
    private final TelegramService telegramService;

    private TelegramBot bot;

    public TelegramBotConnection(EntitiesService entitiesService, TelegramService telegramService) {
        this.entitiesService = entitiesService;
        this.telegramService = telegramService;
    }

    @PostConstruct
    public void createConnection() {
        bot = new TelegramBot("1831860679:AAHbhxCvA0eHzQ-E48ESxjS1faD-Q_hGf1Q");
        bot.setUpdatesListener(new TelegramUpdatesListener());
    }
}
