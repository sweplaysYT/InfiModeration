package me.sweplays.infimoderation.conversations;

import me.sweplays.infimoderation.inventories.BanConfirmInventory;
import org.bukkit.OfflinePlayer;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;
import org.bukkit.entity.Player;

public class BanReasonConversation extends StringPrompt {

    private static String reason;

    OfflinePlayer target;
    public BanReasonConversation(OfflinePlayer target) {
        this.target = target;
    }

    @Override
    public Prompt acceptInput(ConversationContext context, String input) {
        reason = input;
        Player player = (Player) context.getForWhom();
        BanConfirmInventory.createInventory(target);
        BanConfirmInventory.getInventory().show(player);
        return null;
    }

    @Override
    public String getPromptText(ConversationContext context) {
        return "§aEnter what reason §7" + target.getName() + " §ashould be banned for";
    }

    public static String getReason() {
        return reason;
    }
}
