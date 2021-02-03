// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class ModelSnake extends EntityModel<Entity> {
	private final ModelRenderer head;
	private final ModelRenderer body;

	public ModelSnake() {
		textureWidth = 32;
		textureHeight = 32;

		head = new ModelRenderer(this);
		head.setRotationPoint(1.0F, 24.0F, -2.0F);
		head.setTextureOffset(0, 8).addBox(-2.0F, -4.0F, -17.0F, 5.0F, 4.0F, 3.0F, 0.0F, false);
		head.setTextureOffset(6, 0).addBox(0.0F, -1.0F, -23.0F, 2.0F, 0.0F, 6.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(1.0F, 24.0F, -2.0F);
		body.setTextureOffset(0, 0).addBox(-1.0F, -2.0F, -14.0F, 3.0F, 2.0F, 6.0F, 0.0F, false);
		body.setTextureOffset(11, 11).addBox(-2.0F, -2.0F, -8.0F, 3.0F, 2.0F, 5.0F, 0.0F, false);
		body.setTextureOffset(13, 3).addBox(-3.0F, -2.0F, -3.0F, 3.0F, 2.0F, 5.0F, 0.0F, false);
		body.setTextureOffset(0, 15).addBox(-4.0F, -2.0F, 2.0F, 3.0F, 2.0F, 4.0F, 0.0F, false);
		body.setTextureOffset(11, 18).addBox(-2.0F, -2.0F, 6.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);
		body.setTextureOffset(0, 0).addBox(-2.0F, -1.0F, 9.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		head.render(matrixStack, buffer, packedLight, packedOverlay);
		body.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
	}
}