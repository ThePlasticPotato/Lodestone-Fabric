package team.lodestar.lodestone.systems.network;

import me.pepperbell.simplenetworking.C2SPacket;
import me.pepperbell.simplenetworking.S2CPacket;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;

import java.util.function.Supplier;

public abstract class LodestoneTwoWayNBTPacket implements S2CPacket, C2SPacket {
    protected CompoundTag data;

    public LodestoneTwoWayNBTPacket(CompoundTag data) {
        this.data = data;
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeNbt(data);
    }


}