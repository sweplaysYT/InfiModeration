package me.sweplays.infimoderation.conversations;

import me.sweplays.infimoderation.InfiModeration;
import org.bukkit.OfflinePlayer;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.NumericPrompt;
import org.bukkit.conversations.Prompt;
import org.bukkit.entity.Player;

public class SetMoneyConversation extends NumericPrompt {

    OfflinePlayer target;
    public SetMoneyConversation(OfflinePlayer target) {
        this.target = target;
    }

    @Override
    protected Prompt acceptValidatedInput(ConversationContext context, Number input) {
        Player player = (Player) context.getForWhom();
        InfiModeration.getEconomy().withdrawPlayer(target, InfiModeration.getEconomy().getBalance(target));
        InfiModeration.getEconomy().depositPlayer(target, input.doubleValue());
        if (target.isOnline()) target.getPlayer().sendMessage("§7" + player.getName() + " §aset your balance to " + input);
        context.getForWhom().sendRawMessage("§aYou set §7" + target.getName() + "'s §abank account to " + input + "$");
        return null;
    }

    @Override
    public String getPromptText(ConversationContext context) {
        return "§aHow much money do you want to set §7" + target.getName() + "'s §abank account to?";
    }
}
