package me.sweplays.infimoderation.conversations;

import me.sweplays.infimoderation.InfiModeration;
import me.sweplays.infimoderation.inventories.MuteConfirmInventory;
import me.sweplays.infimoderation.inventories.WarnConfirmInventory;
import org.bukkit.OfflinePlayer;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;
import org.bukkit.entity.Player;

public class WarnReasonConversation extends StringPrompt {

    private static String reason;

    OfflinePlayer target;
    public WarnReasonConversation(OfflinePlayer target) {
        this.target = target;
    }

    @Override
    public Prompt acceptInput(ConversationContext context, String input) {
        reason = input;
        Player player = (Player) context.getForWhom();
        WarnConfirmInventory.createInventory(target);
        WarnConfirmInventory.getInventory().show(player);
        return null;
    }

    @Override
    public String getPromptText(ConversationContext context) {
        return "§aEnter what reason §7" + target.getName() + " §ashould be muted for";
    }

    public static String getReason() {
        return reason;
    }
}
