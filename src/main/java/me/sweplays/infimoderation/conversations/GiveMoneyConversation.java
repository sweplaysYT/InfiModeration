package me.sweplays.infimoderation.conversations;

import me.sweplays.infimoderation.InfiModeration;
import org.bukkit.OfflinePlayer;
import org.bukkit.conversations.*;
import org.bukkit.entity.Player;

public class GiveMoneyConversation extends NumericPrompt {

    OfflinePlayer target;
    public GiveMoneyConversation(OfflinePlayer target) {
        this.target = target;
    }

    ConversationCanceller conversationCanceller = new ConversationCanceller() {
        @Override
        public void setConversation(Conversation conversation) {

        }

        @Override
        public boolean cancelBasedOnInput(ConversationContext context, String input) {
            return true;
        }

        @Override
        public ConversationCanceller clone() {
            return null;
        }
    };

    @Override
    protected Prompt acceptValidatedInput(ConversationContext context, Number input) {
        Player player = (Player) context.getForWhom();
        InfiModeration.getEconomy().depositPlayer(target, input.doubleValue());
        if (target.isOnline()) target.getPlayer().sendMessage("§aYou got " + input + "$ from §7" + player.getName());
        context.getForWhom().sendRawMessage("§aYou gave " + input + "$ to §7" + target.getName());
        return null;
    }

    @Override
    public String getPromptText(ConversationContext context) {
        return "§aHow much money do you want to give to §7" + target.getName() + "§a?";
    }
}
