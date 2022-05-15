package me.sweplays.infimoderation.conversations;

import me.sweplays.infimoderation.InfiModeration;
import org.bukkit.OfflinePlayer;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.NumericPrompt;
import org.bukkit.conversations.Prompt;
import org.bukkit.entity.Player;

public class TakeMoneyConversation extends NumericPrompt {

    OfflinePlayer target;

    public TakeMoneyConversation(OfflinePlayer target) {
        this.target = target;
    }

    @Override
    protected Prompt acceptValidatedInput(ConversationContext context, Number input) {
        Player player = (Player) context.getForWhom();
        InfiModeration.getEconomy().withdrawPlayer(target, input.doubleValue());
        if (target.isOnline()) target.getPlayer().sendMessage("§7" + player.getName() + " §atook " + input + "$ from you");
        context.getForWhom().sendRawMessage("§aYou took " + input + "$ from §7" + target.getName() + "'s §abank account");
        return null;
    }

    @Override
    public String getPromptText(ConversationContext context) {
        return "§aHow much money do you want to take from §7" + target.getName() + "'s §abank account?";
    }
}
