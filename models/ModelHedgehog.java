// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class ModelHedgehog extends EntityModel<Entity> {
	private final ModelRenderer snout;
	private final ModelRenderer body;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;
	private final ModelRenderer cube_r5;
	private final ModelRenderer cube_r6;

	public ModelHedgehog() {
		textureWidth = 32;
		textureHeight = 32;

		snout = new ModelRenderer(this);
		snout.setRotationPoint(0.0F, 24.0F, -3.0F);
		snout.setTextureOffset(0, 14).addBox(-2.0F, -3.0F, -2.0F, 4.0F, 3.0F, 3.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, -3.0F);
		body.setTextureOffset(0, 0).addBox(-4.0F, -6.0F, 1.0F, 8.0F, 6.0F, 8.0F, 0.0F, false);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(2.0F, -6.0F, 3.0F);
		body.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, -2.3562F, 0.0F);
		cube_r1.setTextureOffset(0, 0).addBox(-2.0F, -1.0F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(-3.0F, -6.0F, 8.0F);
		body.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, -2.3562F, 0.0F);
		cube_r2.setTextureOffset(4, 0).addBox(-2.0F, -1.0F, 0.0F, 2.0F, 1.0F, 0.0F, 0.0F, false);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(-1.0F, -6.0F, 5.0F);
		body.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, 2.3562F, 0.0F);
		cube_r3.setTextureOffset(0, 4).addBox(-3.0F, -1.0F, 1.0F, 4.0F, 1.0F, 0.0F, 0.0F, false);

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(2.0F, -6.0F, 8.0F);
		body.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, 2.3562F, 0.0F);
		cube_r4.setTextureOffset(0, 1).addBox(-1.0F, -1.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, false);

		cube_r5 = new ModelRenderer(this);
		cube_r5.setRotationPoint(-1.0F, -6.0F, 2.0F);
		body.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.0F, -0.7854F, 0.0F);
		cube_r5.setTextureOffset(4, 1).addBox(-2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.0F, 0.0F, false);

		cube_r6 = new ModelRenderer(this);
		cube_r6.setRotationPoint(1.0F, -6.0F, 4.0F);
		body.addChild(cube_r6);
		setRotationAngle(cube_r6, 0.0F, 0.7854F, 0.0F);
		cube_r6.setTextureOffset(0, 3).addBox(-3.0F, -1.0F, 0.0F, 4.0F, 1.0F, 0.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		snout.render(matrixStack, buffer, packedLight, packedOverlay);
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