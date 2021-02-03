// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class Modeltiger extends EntityModel<Entity> {
	private final ModelRenderer body;
	private final ModelRenderer neck_r1;
	private final ModelRenderer front_body_r1;
	private final ModelRenderer buttock_r1;
	private final ModelRenderer back_body_r1;
	private final ModelRenderer tail;
	private final ModelRenderer tail_r1;
	private final ModelRenderer tail_r2;
	private final ModelRenderer tail_r3;
	private final ModelRenderer tail_r4;
	private final ModelRenderer back_leg_left;
	private final ModelRenderer leg1_r1;
	private final ModelRenderer leg0_r1;
	private final ModelRenderer front_leg_left;
	private final ModelRenderer leg3_r1;
	private final ModelRenderer leg2_r1;
	private final ModelRenderer head;
	private final ModelRenderer nose_r1;
	private final ModelRenderer ear_r1;
	private final ModelRenderer ear_r2;
	private final ModelRenderer front_leg_right;
	private final ModelRenderer leg4_r1;
	private final ModelRenderer leg3_r2;
	private final ModelRenderer back_leg_right;
	private final ModelRenderer leg2_r2;
	private final ModelRenderer leg1_r2;

	public Modeltiger() {
		textureWidth = 128;
		textureHeight = 128;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 11.0F, 2.0F);

		neck_r1 = new ModelRenderer(this);
		neck_r1.setRotationPoint(0.0F, -1.0566F, -10.4736F);
		body.addChild(neck_r1);
		setRotationAngle(neck_r1, 1.693F, 0.0F, 0.0F);
		neck_r1.setTextureOffset(37, 0).addBox(-5.0F, -1.4434F, -4.5264F, 10.0F, 3.0F, 10.0F, 0.0F, false);

		front_body_r1 = new ModelRenderer(this);
		front_body_r1.setRotationPoint(0.0F, 3.0F, -3.0F);
		body.addChild(front_body_r1);
		setRotationAngle(front_body_r1, 1.5621F, 0.0F, 0.0F);
		front_body_r1.setTextureOffset(0, 0).addBox(-6.0F, -7.0F, -3.4F, 12.0F, 12.0F, 13.0F, 0.0F, false);

		buttock_r1 = new ModelRenderer(this);
		buttock_r1.setRotationPoint(0.0F, -0.9264F, 14.6296F);
		body.addChild(buttock_r1);
		setRotationAngle(buttock_r1, 1.501F, 0.0F, 0.0F);
		buttock_r1.setTextureOffset(32, 38).addBox(-5.0F, -2.0F, -5.0F, 10.0F, 4.0F, 10.0F, -0.1F, false);

		back_body_r1 = new ModelRenderer(this);
		back_body_r1.setRotationPoint(0.0F, 3.0F, 13.0F);
		body.addChild(back_body_r1);
		setRotationAngle(back_body_r1, 1.5882F, 0.0F, 0.0F);
		back_body_r1.setTextureOffset(0, 25).addBox(-5.0F, -11.0F, -2.0F, 10.0F, 12.0F, 11.0F, 0.0F, false);

		tail = new ModelRenderer(this);
		tail.setRotationPoint(0.0F, -1.26F, 16.8127F);
		body.addChild(tail);
		setRotationAngle(tail, 0.2618F, 0.0F, 0.0F);

		tail_r1 = new ModelRenderer(this);
		tail_r1.setRotationPoint(0.0F, 7.5F, 8.0F);
		tail.addChild(tail_r1);
		setRotationAngle(tail_r1, 1.6319F, 0.0F, 0.0F);
		tail_r1.setTextureOffset(0, 0).addBox(-1.5F, -0.2744F, -0.1374F, 3.0F, 6.0F, 3.0F, -0.51F, false);

		tail_r2 = new ModelRenderer(this);
		tail_r2.setRotationPoint(0.0F, 4.0F, 2.75F);
		tail.addChild(tail_r2);
		setRotationAngle(tail_r2, 1.1083F, 0.0F, 0.0F);
		tail_r2.setTextureOffset(0, 0).addBox(-1.5F, -0.2643F, -0.9271F, 3.0F, 7.0F, 3.0F, -0.5F, false);

		tail_r3 = new ModelRenderer(this);
		tail_r3.setRotationPoint(0.0F, -1.5F, 1.0F);
		tail.addChild(tail_r3);
		setRotationAngle(tail_r3, 1.1956F, 0.0F, 0.0F);
		tail_r3.setTextureOffset(0, 0).addBox(-1.5F, -3.0687F, -2.9125F, 3.0F, 3.0F, 3.0F, -0.2F, false);

		tail_r4 = new ModelRenderer(this);
		tail_r4.setRotationPoint(0.0F, -0.75F, 0.75F);
		tail.addChild(tail_r4);
		setRotationAngle(tail_r4, 0.7156F, 0.0F, 0.0F);
		tail_r4.setTextureOffset(0, 0).addBox(-1.5F, -1.0F, -2.25F, 3.0F, 7.0F, 3.0F, -0.4F, false);

		back_leg_left = new ModelRenderer(this);
		back_leg_left.setRotationPoint(4.0F, 14.5F, 13.5F);
		back_leg_left.setTextureOffset(61, 61).addBox(-1.0F, 8.5F, -3.5F, 3.0F, 1.0F, 5.0F, 0.2F, false);

		leg1_r1 = new ModelRenderer(this);
		leg1_r1.setRotationPoint(1.0F, 6.5F, 0.5F);
		back_leg_left.addChild(leg1_r1);
		setRotationAngle(leg1_r1, -0.1309F, 0.0F, 0.0F);
		leg1_r1.setTextureOffset(38, 52).addBox(-2.0F, -5.3F, -2.0F, 3.0F, 8.0F, 4.0F, 0.0F, false);

		leg0_r1 = new ModelRenderer(this);
		leg0_r1.setRotationPoint(1.0F, 1.5F, 0.5F);
		back_leg_left.addChild(leg0_r1);
		setRotationAngle(leg0_r1, 0.1745F, 0.0F, 0.0F);
		leg0_r1.setTextureOffset(20, 52).addBox(-2.5F, -5.0F, -2.3F, 4.0F, 8.0F, 5.0F, 0.0F, false);

		front_leg_left = new ModelRenderer(this);
		front_leg_left.setRotationPoint(6.0F, 15.0F, -4.0F);
		front_leg_left.setTextureOffset(61, 61).addBox(-1.5F, 8.0F, -3.7F, 3.0F, 1.0F, 5.0F, 0.2F, false);

		leg3_r1 = new ModelRenderer(this);
		leg3_r1.setRotationPoint(0.0F, 5.0F, -1.0F);
		front_leg_left.addChild(leg3_r1);
		setRotationAngle(leg3_r1, -0.1309F, 0.0F, 0.0F);
		leg3_r1.setTextureOffset(52, 52).addBox(-1.5F, -3.2F, -1.0F, 3.0F, 7.0F, 4.0F, 0.0F, false);

		leg2_r1 = new ModelRenderer(this);
		leg2_r1.setRotationPoint(1.0F, 0.0F, -1.0F);
		front_leg_left.addChild(leg2_r1);
		setRotationAngle(leg2_r1, 0.0436F, 0.0F, 0.0F);
		leg2_r1.setTextureOffset(0, 48).addBox(-3.0F, -4.0F, -2.0F, 4.0F, 8.0F, 6.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 10.272F, -10.9565F);
		setRotationAngle(head, 0.0349F, 0.0F, 0.0F);

		nose_r1 = new ModelRenderer(this);
		nose_r1.setRotationPoint(0.0F, -1.0692F, -1.9639F);
		head.addChild(nose_r1);
		setRotationAngle(nose_r1, 1.5708F, 0.0F, 0.0F);
		nose_r1.setTextureOffset(0, 25).addBox(-2.0F, -4.9809F, -0.3021F, 4.0F, 3.0F, 1.0F, 0.0F, false);
		nose_r1.setTextureOffset(42, 31).addBox(-3.0F, -5.0809F, -4.3021F, 6.0F, 3.0F, 4.0F, 0.0F, false);
		nose_r1.setTextureOffset(42, 17).addBox(-4.0F, -2.0809F, -4.3021F, 8.0F, 6.0F, 8.0F, 0.0F, false);

		ear_r1 = new ModelRenderer(this);
		ear_r1.setRotationPoint(-2.5F, -5.5257F, -1.7859F);
		head.addChild(ear_r1);
		setRotationAngle(ear_r1, 1.5708F, 0.0F, -0.2618F);
		ear_r1.setTextureOffset(50, 13).addBox(-1.8669F, -0.2589F, -1.7497F, 3.0F, 1.0F, 3.0F, 0.0F, false);

		ear_r2 = new ModelRenderer(this);
		ear_r2.setRotationPoint(2.5F, -5.5257F, -1.7859F);
		head.addChild(ear_r2);
		setRotationAngle(ear_r2, 1.5708F, 0.0F, 0.2618F);
		ear_r2.setTextureOffset(50, 13).addBox(-1.1331F, -0.2589F, -1.7498F, 3.0F, 1.0F, 3.0F, 0.0F, false);

		front_leg_right = new ModelRenderer(this);
		front_leg_right.setRotationPoint(-5.0F, 15.0F, -4.0F);
		front_leg_right.setTextureOffset(61, 61).addBox(-2.5F, 8.0F, -3.7F, 3.0F, 1.0F, 5.0F, 0.2F, true);

		leg4_r1 = new ModelRenderer(this);
		leg4_r1.setRotationPoint(-1.0F, 5.0F, -1.0F);
		front_leg_right.addChild(leg4_r1);
		setRotationAngle(leg4_r1, -0.1309F, 0.0F, 0.0F);
		leg4_r1.setTextureOffset(52, 52).addBox(-1.5F, -3.2F, -1.0F, 3.0F, 7.0F, 4.0F, 0.0F, true);

		leg3_r2 = new ModelRenderer(this);
		leg3_r2.setRotationPoint(0.0F, 0.0F, 0.0F);
		front_leg_right.addChild(leg3_r2);
		setRotationAngle(leg3_r2, 0.0436F, 0.0F, 0.0F);
		leg3_r2.setTextureOffset(0, 48).addBox(-3.0F, -4.0436F, -2.999F, 4.0F, 8.0F, 6.0F, 0.0F, true);

		back_leg_right = new ModelRenderer(this);
		back_leg_right.setRotationPoint(-5.0F, 14.5F, 13.5F);
		back_leg_right.setTextureOffset(61, 61).addBox(-1.0F, 8.5F, -3.5F, 3.0F, 1.0F, 5.0F, 0.2F, true);

		leg2_r2 = new ModelRenderer(this);
		leg2_r2.setRotationPoint(1.0F, 6.5F, 0.5F);
		back_leg_right.addChild(leg2_r2);
		setRotationAngle(leg2_r2, -0.1309F, 0.0F, 0.0F);
		leg2_r2.setTextureOffset(38, 52).addBox(-2.0F, -5.3F, -2.0F, 3.0F, 8.0F, 4.0F, 0.0F, true);

		leg1_r2 = new ModelRenderer(this);
		leg1_r2.setRotationPoint(1.0F, 1.5F, 0.5F);
		back_leg_right.addChild(leg1_r2);
		setRotationAngle(leg1_r2, 0.1745F, 0.0F, 0.0F);
		leg1_r2.setTextureOffset(20, 52).addBox(-2.5F, -5.0F, -2.3F, 4.0F, 8.0F, 5.0F, 0.0F, true);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		back_leg_left.render(matrixStack, buffer, packedLight, packedOverlay);
		front_leg_left.render(matrixStack, buffer, packedLight, packedOverlay);
		head.render(matrixStack, buffer, packedLight, packedOverlay);
		front_leg_right.render(matrixStack, buffer, packedLight, packedOverlay);
		back_leg_right.render(matrixStack, buffer, packedLight, packedOverlay);
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