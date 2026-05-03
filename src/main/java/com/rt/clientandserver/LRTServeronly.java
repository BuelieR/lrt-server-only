package com.rt.clientandserver;

import net.fabricmc.api.ModInitializer;

import static net.minecraft.server.command.CommandManager.*;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.minecraft.registry.RegistryKeys.ITEM_GROUP;

public class LRTServeronly implements ModInitializer {
	public static final String Context_of_the_help_of_the_command_of_server =
			"LRT服务器命令指南 | The help of LRT server's commands\n" +
					"/we(或/worldedit): 创世神模组的帮助命令\n" +
					"/wrap: 主城传送命令";
	public static final String MOD_ID = "lrt-server-only";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final Item TODO_LIST = new Item(new FabricItemSettings().maxCount(1));

	public static final ItemGroup LRT_ITEM_GROUP = Registry.register(
			Registries.ITEM_GROUP,
			new Identifier("lrt-server-only", "todo_list"),
			FabricItemGroup.builder()
					.icon(() -> new ItemStack(LRTServeronly.TODO_LIST))
					.displayName(Text.translatable("itemGroup.lrt-server-only.LRT"))
					.entries((context, entries) -> {
						// 添加物品
						entries.add(TODO_LIST);
						// 添加更多物品
					})
					.build()
	);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		CommandRegistrationCallback.EVENT.register(((commandDispatcher, commandRegistryAccess, registrationEnvironment) -> commandDispatcher.register(
				literal("shp").executes(commandContext -> {
					commandContext.getSource().sendFeedback(() -> Text.literal(Context_of_the_help_of_the_command_of_server),false);
                    return 1;
                })
		)));
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "todo_list"), TODO_LIST);
		//Registry.register(Registries.ITEM_GROUP, new Identifier(MOD_ID, "lrt_group"), LRT_ITEM_GROUP);
		LOGGER.info("Loaded mod that named LRT server only successfully...");
	}
}