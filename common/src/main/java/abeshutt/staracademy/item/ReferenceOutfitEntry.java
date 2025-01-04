package abeshutt.staracademy.item;

import abeshutt.staracademy.data.adapter.Adapters;
import abeshutt.staracademy.init.ModConfigs;
import abeshutt.staracademy.init.ModOutfits;
import abeshutt.staracademy.item.renderer.OutfitItemRenderer;
import abeshutt.staracademy.outfit.core.OutfitPiece;
import abeshutt.staracademy.world.random.RandomSource;
import com.google.gson.JsonObject;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class ReferenceOutfitEntry extends OutfitEntry {

    private String id;

    public ReferenceOutfitEntry() {

    }

    @Override
    public Optional<OutfitEntry> flatten(RandomSource random) {
        return ModConfigs.WARDROBE.get(this.id).flatMap(entry -> entry.flatten(random));
    }

    @Override
    public void render(OutfitItemRenderer renderer, ItemStack stack, ModelTransformationMode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {

    }

    @Override
    public Optional<NbtCompound> writeNbt() {
        return super.writeNbt().map(nbt -> {
            Adapters.UTF_8.writeNbt(this.id).ifPresent(tag -> nbt.put("id", tag));
            return nbt;
        });
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.id = Adapters.UTF_8.readNbt(nbt.get("id")).orElse(null);
    }

    @Override
    public Optional<JsonObject> writeJson() {
        return super.writeJson().map(json -> {
            Adapters.UTF_8.writeJson(this.id).ifPresent(tag -> json.add("id", tag));
            return json;
        });
    }

    @Override
    public void readJson(JsonObject json) {
        super.readJson(json);
        this.id = Adapters.UTF_8.readJson(json.get("id")).orElse(null);
    }

}
