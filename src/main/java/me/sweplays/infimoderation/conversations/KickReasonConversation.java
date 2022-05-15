package me.sweplays.infimoderation.conversations;

import me.sweplays.infimoderation.InfiModeration;
import me.sweplays.infimoderation.inventories.BanConfirmInventory;
import org.bukkit.OfflinePlayer;
import org.bukkit.conversations.*;
import org.bukkit.entity.Player;

public class KickReasonConversation extends StringPrompt {

    private InfiModeration plugin = InfiModeration.getPlugin();

    private static String reason;

    OfflinePlayer target;
    public KickReasonConversation(OfflinePlayer target) {
        this.target = target;
    }

    @Override
    public Prompt acceptInput(ConversationContext context, String input) {
        reason = input;
        Player player = (Player) context.getForWhom();
        if (plugin.getConfig().getBoolean("enable-advancedbans")) {
            player.getPlayer().performCommand("kick " + target.getName() + " " + KickReasonConversation.getReason());
        } else {
            if (target.isOnline()) target.getPlayer().kickPlayer("You have been kicked by " + player.getName() + " Reason: " + KickReasonConversation.getReason());
        }
        return null;
    }

    @Override
    public String getPromptText(ConversationContext context) {
        return "§aEnter what reason §7" + target.getName() + " §ashould be kicked for";
    }

    public static String getReason() {
        return reason;
    }
}
