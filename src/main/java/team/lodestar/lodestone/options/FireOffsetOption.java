package team.lodestar.lodestone.options;

import io.github.fabricators_of_create.porting_lib.event.client.OverlayRenderCallback;
import team.lodestar.lodestone.config.ClientConfig;
import team.lodestar.lodestone.data.LodestoneLangDatagen;
import team.lodestar.lodestone.systems.option.LodestoneOption;
import net.minecraft.client.ProgressOption;
import net.minecraft.client.gui.screens.AccessibilityOptionsScreen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

public class FireOffsetOption extends ProgressOption implements LodestoneOption {

    private static final Component TOOLTIP = new TranslatableComponent(LodestoneLangDatagen.getOptionTooltip("fire_offset"));

    public FireOffsetOption() {
        super(LodestoneLangDatagen.getOption("fire_offset"),
                0.0D,
                1.0D,
                0.01F,
                (options) -> ClientConfig.FIRE_OVERLAY_OFFSET.getConfigValue(),
                (options, value) -> ClientConfig.FIRE_OVERLAY_OFFSET.setConfigValue(Math.round(value * 100d) / 100d),
                (options, progressOption) -> {
                    double value = progressOption.toPct(progressOption.get(options));
                    return value == 0.0D ? progressOption.genericValueLabel(CommonComponents.OPTION_OFF) : progressOption.percentValueLabel(value);
                },
                (minecraft) -> minecraft.font.split(TOOLTIP, 200));
    }

}