package com.github.legendarynetherstarfurnace;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

// Esta anotação avisa o NeoForge para ler os eventos desta classe automaticamente
@EventBusSubscriber(modid = "legendarynetherstarfurnace")
public class ModEvents {

    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();

        // Garante que a mensagem só seja enviada pelo servidor (evita mensagem duplicada)
        if (!player.level().isClientSide()) {

            // Prefixo do Mod em Roxo Escuro e Negrito
            Component prefix = Component.literal("[Cosmic Forge] ").withStyle(ChatFormatting.DARK_PURPLE, ChatFormatting.BOLD);

            // Mensagem em Português
            Component ptMessage = Component.literal("Legendary Netherstar Furnace Mod está em período de desenvolvimento e aprimoramento visual, caso identifique qualquer erro, comente na pagina do curseforge, ou encaminhe um email para qewr8478@gmail.com")
                    .withStyle(ChatFormatting.WHITE, ChatFormatting.ITALIC);

            // Mensagem em Inglês
            Component enMessage = Component.literal("Legendary Netherstar Furnace Mod is currently in development and visual improvement phase. If you find any bugs, please comment on the CurseForge page, or send an email to qewr8478@gmail.com")
                    .withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC);

            // Envia para o jogador
            player.sendSystemMessage(prefix.copy().append(ptMessage));
            player.sendSystemMessage(prefix.copy().append(enMessage));
        }
    }
}