package net.primegames.primefactions.groups;

import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.Faction;
import lombok.Getter;
import lombok.NonNull;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.data.DataMutateResult;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.NodeEqualityPredicate;
import net.luckperms.api.node.types.InheritanceNode;
import net.luckperms.api.util.Tristate;
import net.primegames.groups.GroupTier;
import net.primegames.primefactions.PrimeFactions;
import net.primegames.utils.ColorUtils;
import net.primegames.utils.LoggerUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public enum FactionsGroups {

    OWNER("owner", GroupTier.OWNER, "&l&c{faction_rank}&3{faction}&f〚&9Owner&f〛&3{player}&e: &b{message}", "•&c{faction_rank}&3{faction}{faction_ln}&f[&l&9Owner&f]&3{player}"),
    CHIEF("chief", GroupTier.CHIEF, "&l&c{faction_rank}&3{faction}&f〚&aChief&f〛&5{player}&6: &a{message}", "•&c{faction_rank}&3{faction}{faction_ln}&f[&aChief&f]&5{player}"),
    LOS("los", GroupTier.LOS, "&l&c{faction_rank}&3{faction}&f〚&aLOS&f〛&a{player}&6: &a{message}", "•&c{faction_rank}&3{faction}{faction_ln}&f[&aLOS&f]&d{player}"),
    ADMIN("admin", GroupTier.ADMIN, "&l&c{faction_rank}&3{faction}&f〚&3Admin&f〛{player}&7: &4{message}", "•&c{faction_rank}&3{faction}{faction_ln}&f[&3Admin&f]&9{player}"),
    MOD("mod", GroupTier.MOD, "&l&c{faction_rank}&3{faction}&f〚&3MOD&f〛{player}&7: &4{message}", "•&c{faction_rank}&3{faction}{faction_ln}&f[&3MOD&a&f]{player}"),
    Trainee("Trainee", GroupTier.TRAINEE, "&l&c{faction_rank}&3{faction}&f〚&eTrainee&f〛{player}&7: &e{message}", "•&c{faction_rank}&3{faction}{faction_ln}&f[&eTrainee&f]&e{player}"),
    MORTAL("mortal", GroupTier.TIER_0, "&c{faction_rank}&3{faction}&f〚&7Mortal&f〛{player}&7: {message}", "•&c{faction_rank}&3{faction}{faction_ln}&f[&7Mortal&f]{player}"),
    VOTER("voter", GroupTier.TIER_1, "&c{faction_rank}&3{faction}&f〚&aVoter&f〛{player}&7: &f{message}", "•&c{faction_rank}&3{faction}{faction_ln}&f[&aVoter&f]&7{player}"),
    ARES("ares", GroupTier.TIER_2, "&c{faction_rank}&3{faction}&f〚&cAres&f〛{player}&7: &c{message}", "•&c{faction_rank}&3{faction}{faction_ln}&f[&cAres&f]&c{player}"),
    IRIS("iris", GroupTier.TIER_3, "&c{faction_rank}&3{faction}&f〚&3Iris&f〛{player}&7: &3{message}", "•&c{faction_rank}&3{faction}{faction_ln}&f[&3Iris&f]&d{player}"),
    POSEIDON("poseidon", GroupTier.TIER_4, "&c{faction_rank}&3{faction}&f〚&6Poseidon&f〛{player}&7: &l&6{message}", "•&c{faction_rank}&3{faction}{faction_ln}&f[&6Poseidon&f]&4{player}"),
    BUILDER("builder", GroupTier.TIER_5, "&l&c{faction_rank}&3{faction}&f〚&6Builder&f〛{player}&7: &6{message}", "•&c{faction_rank}&3{faction}{faction_ln}&f[&6Builder&f]&6{player}"),
    HADES("hades", GroupTier.TIER_6, "&l&c{faction_rank}&3{faction}&f〚&5Hades&f〛{player}&7: &5{message}", "•&c{faction_rank}&3{faction}{faction_ln}&f[&5Hades&f]&4{player}"),
    ZEUS("zeus", GroupTier.TIER_8, "&l&c{faction_rank}&3{faction}&f〚&bZeus&f〛{player}&7: &b{message}", "•&c{faction_rank}&3{faction}{faction_ln}&f[&bZeus&f]&6{player}"),
    TITAN("titan", GroupTier.TIER_9, "&l&c{faction_rank}&3{faction}&f〚&aTitan&f〛{player}&7: &a{message}", "•&c{faction_rank}&3{faction}{faction_ln}&f[&a&lTitan&r&f]&a{player}&r");

    @Getter
    private final String name;
    @Getter
    private final GroupTier tier;
    @Getter
    @NonNull
    private final Group group;
    private final String displayName;
    private final String chatFormat;

    FactionsGroups(String name, GroupTier groupTier, String chatFormat, String displayName) {
        this.tier = groupTier;
        this.name = name;
        Group group = PrimeFactions.getInstance().getLuckPerms().getGroupManager().getGroup(name);
        if (group != null) {
            this.group = group;
        } else {
            throw new IllegalArgumentException("Group named: " + name + " does not exist in LuckPerms!");
        }
        this.displayName = displayName;
        this.chatFormat = chatFormat;
    }

    public static List<@NonNull FactionsGroups> fromTiers(List<GroupTier> tiers) {
        List<@NonNull FactionsGroups> groups = new ArrayList<>();
        FactionsGroups[] factionsGroups = FactionsGroups.values();
        for (FactionsGroups factionsGroup : factionsGroups) {
            for (GroupTier tier : tiers) {
                if (factionsGroup.getTier().equals(tier)) {
                    groups.add(factionsGroup);
                }
            }
        }
        return groups;
    }

    public static FactionsGroups fromTier(GroupTier groupTier) {
        FactionsGroups[] groups = FactionsGroups.values();
        for (FactionsGroups group : groups) {
            if (group.getTier().equals(groupTier)) {
                return group;
            }
        }
        return null;
    }

    public String getChatFormatFor(Player player, String message) {
        FPlayer me = FPlayers.getInstance().getByPlayer(player);
        String chatFormat = this.chatFormat;
        Faction factions = me.getFaction();
        if (factions == null || factions.isWilderness()) {
            chatFormat = chatFormat.replace("{faction}", "").replace("{faction_rank}", "");
        } else {
                chatFormat = chatFormat.replace("{faction}", factions.getTag()).replace("{faction_rank}", me.getRole().getPrefix());
        }
        chatFormat = chatFormat.replace("{player}", player.getName());
        chatFormat = chatFormat.replace("{message}", message);
        return ColorUtils.getColString(chatFormat);
    }

    public String getDisplayNameFor(Player player) {
        FPlayer me = FPlayers.getInstance().getByPlayer(player);
        String displayName = this.displayName;
        Faction factions = me.getFaction();
        if (factions == null) {
            displayName = displayName.replace("{faction}", "").replace("{faction_rank}", "").replace("{faction_ln}", "");
        } else {
            displayName = displayName.replace("{faction}", factions.getTag()).replace("{faction_rank}", me.getRole().getPrefix()).replace("{faction_ln}", "\n");
        }
        displayName = displayName.replace("{player}", player.getName());
        return ColorUtils.getColString(displayName);
    }


    public static FactionsGroups fromTierId(int id) {
        FactionsGroups[] groups = FactionsGroups.values();
        for (FactionsGroups group : groups) {
            if (group.getTier().getId() == id) {
                return group;
            }
        }
        return null;
    }

    public static FactionsGroups getHighestPriority(List<FactionsGroups> groups) {
        FactionsGroups highestPriority = FactionsGroups.MORTAL;
        for (FactionsGroups factionsGroup : groups) {
            if (highestPriority.getTier().getPriority() < factionsGroup.getTier().getPriority()) {
                highestPriority = factionsGroup;
            }
        }
        return highestPriority;
    }

    public static void addGroupsFromTiers(@NonNull Player player, List<@NonNull GroupTier> tiers) {
        List<FactionsGroups> factionsGroups = fromTiers(tiers);
        LoggerUtils.info(ChatColor.YELLOW + "Loading factions groups for " + player.getName());
        LuckPerms luckPerms = PrimeFactions.getInstance().getLuckPerms();
        User user = luckPerms.getUserManager().getUser(player.getUniqueId());
        if (user != null) {
            user.getNodes().clear();
            for (FactionsGroups factionsGroup : factionsGroups) {
                InheritanceNode node = InheritanceNode.builder(factionsGroup.getGroup().getName()).value(true).build();
                if (user.data().contains(node, NodeEqualityPredicate.EXACT).equals(Tristate.FALSE) || user.data().contains(node, NodeEqualityPredicate.EXACT).equals(Tristate.UNDEFINED)) {
                    if (user.data().add(node) == DataMutateResult.SUCCESS) {
                        LoggerUtils.debug("Added factions group " + factionsGroup.getGroup().getName() + " to " + player.getName());
                    } else {
                        LoggerUtils.error("Failed to add factions group " + factionsGroup.getGroup().getName() + " to " + player.getName());
                    }
                }
            }
            FactionsGroups finalGroup = FactionsGroups.getHighestPriority(factionsGroups);
            if(user.getPrimaryGroup().equals(finalGroup.getGroup().getName())) {
                return;
            }else if (user.setPrimaryGroup(finalGroup.getGroup().getName()) == DataMutateResult.SUCCESS) {
                LoggerUtils.success("Set " + player.getName() + "'s primary group to " + finalGroup.getGroup().getName());
            } else {
                LoggerUtils.error("Failed to set " + player.getName() + "'s primary group to " + finalGroup.getGroup().getName());
            }
            luckPerms.getUserManager().saveUser(user);
        } else {
            LoggerUtils.error("Failed to get LuckPerms User for " + player.getName());
        }
    }


    @NonNull
    public static FactionsGroups getGroupFor(Player player){
        LuckPerms luckPerms = PrimeFactions.getInstance().getLuckPerms();
        User user = luckPerms.getUserManager().getUser(player.getUniqueId());
        if (user != null) {
            for (FactionsGroups factionsGroup : values()) {
                if (!factionsGroup.equals(MORTAL) && user.getPrimaryGroup().equals(factionsGroup.getName())) {
                    return factionsGroup;
                }
            }
        }
        return MORTAL;
    }

}
