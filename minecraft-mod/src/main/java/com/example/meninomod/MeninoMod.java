package com.example.meninomod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class MeninoMod implements ModInitializer {
    public static final String MOD_ID = "meninomod";

    public static final Item AMULETO_DO_MENINO = new Item(
            new Item.Settings()
    );

    @Override
    public void onInitialize() {
        Registry.register(
                Registries.ITEM,
                Identifier.of(MOD_ID, "amuleto_do_menino"),
                AMULETO_DO_MENINO
        );

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS)
                .register(entries -> entries.add(AMULETO_DO_MENINO));

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(
                    CommandManager.literal("oi")
                            .executes(context -> {
                                context.getSource().sendFeedback(
                                        () -> Text.literal("Olá! Este é o Menino Mod."),
                                        false
                                );
                                return 1;
                            })
            );
        });
    }
}
