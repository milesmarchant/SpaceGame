package physics;

import java.awt.Graphics2D;

import org.junit.Assert;
import org.junit.Test;

import physics.Collision.CircleHitBox;
import physics.Collision.HitBox;
import physics.Collision.PointHitBox;
import physics.Collision.RectHitBox;
import scene.Scene;
import entity.Entity;

public class CollisionTest {

	@Test
	public void testPP() {
		PointEntity p1 = new PointEntity(0, 0, 0, 1);
		PointEntity p2 = new PointEntity(0, 0, 1, 0);
		Assert.assertTrue("Overlapping points", p1.getHitBox().collideWith(p2.getHitBox()));
		p1 = new PointEntity(0, 0, 0, 1);
		p2 = new PointEntity(1, 1, 1, 0);
		Assert.assertFalse("Non-overlapping points", p1.getHitBox().collideWith(p2.getHitBox()));
	}
	
	@Test
	public void testPR() {
		PointEntity p1 = new PointEntity(0, 0, 0, 1);
		RectEntity r1 = new RectEntity(0, 0, 0, new double[]{1,-1,-1,1}, new double[]{1,1,-1,-1}, 1, 0);
		Assert.assertTrue("Overlapping point and non-rotated rectangle", p1.getHitBox().collideWith(r1.getHitBox()));
		p1 = new PointEntity(10, 10, 0, 1);
		r1 = new RectEntity(0, 0, 0, new double[]{1,-1,-1,1}, new double[]{1,1,-1,-1}, 1, 0);
		Assert.assertFalse("Non-overlapping point and non-rotated rectangle", p1.getHitBox().collideWith(r1.getHitBox()));

		p1 = new PointEntity(0, 0, 0, 1);
		r1 = new RectEntity(0, 0, 0, new double[]{4,-4,-4,4}, new double[]{1500,1500,-1500,-1500}, 1, 0);
		Assert.assertTrue("Overlapping point and non-rotated, tall rectangle", p1.getHitBox().collideWith(r1.getHitBox()));
		p1 = new PointEntity(10, 10, 0, 1);
		r1 = new RectEntity(0, 0, 0, new double[]{4,-4,-4,4}, new double[]{1500,1500,-1500,-1500}, 1, 0);
		Assert.assertFalse("Non-overlapping point and non-rotated, tall rectangle", p1.getHitBox().collideWith(r1.getHitBox()));
		
		p1 = new PointEntity(0, 0, 0, 1);
		r1 = new RectEntity(0, 0, 45, new double[]{1,-1,-1,1}, new double[]{1,1,-1,-1}, 1, 0);
		Assert.assertTrue("Overlapping point and 45 degree rectangle", p1.getHitBox().collideWith(r1.getHitBox()));
		p1 = new PointEntity(10, 10, 0, 1);
		r1 = new RectEntity(0, 0, 45, new double[]{1,-1,-1,1}, new double[]{1,1,-1,-1}, 1, 0);
		Assert.assertFalse("Non-overlapping point and 45 degree rectangle", p1.getHitBox().collideWith(r1.getHitBox()));
		
		p1 = new PointEntity(0, 0, 0, 1);
		r1 = new RectEntity(0, 0, 90, new double[]{1,-1,-1,1}, new double[]{1,1,-1,-1}, 1, 0);
		Assert.assertTrue("Overlapping point and 90 degree rectangle", p1.getHitBox().collideWith(r1.getHitBox()));
		p1 = new PointEntity(10, 10, 0, 1);
		r1 = new RectEntity(0, 0, 90, new double[]{1,-1,-1,1}, new double[]{1,1,-1,-1}, 1, 0);
		Assert.assertFalse("Non-overlapping point and 90 degree rectangle", p1.getHitBox().collideWith(r1.getHitBox()));
		
		p1 = new PointEntity(0, 0, 0, 1);
		r1 = new RectEntity(0, 0, 135, new double[]{1,-1,-1,1}, new double[]{1,1,-1,-1}, 1, 0);
		Assert.assertTrue("Overlapping point and 135 degree rectangle", p1.getHitBox().collideWith(r1.getHitBox()));
		p1 = new PointEntity(10, 10, 0, 1);
		r1 = new RectEntity(0, 0, 135, new double[]{1,-1,-1,1}, new double[]{1,1,-1,-1}, 1, 0);
		Assert.assertFalse("Non-overlapping point and 135 degree rectangle", p1.getHitBox().collideWith(r1.getHitBox()));
		
		p1 = new PointEntity(0, 0, 0, 1);
		r1 = new RectEntity(0, 0, 180, new double[]{1,-1,-1,1}, new double[]{1,1,-1,-1}, 1, 0);
		Assert.assertTrue("Overlapping point and 180 degree rectangle", p1.getHitBox().collideWith(r1.getHitBox()));
		p1 = new PointEntity(10, 10, 0, 1);
		r1 = new RectEntity(0, 0, 180, new double[]{1,-1,-1,1}, new double[]{1,1,-1,-1}, 1, 0);
		Assert.assertFalse("Non-overlapping point and 180 degree rectangle", p1.getHitBox().collideWith(r1.getHitBox()));
		
		p1 = new PointEntity(0, 0, 0, 1);
		r1 = new RectEntity(0, 0, 225, new double[]{1,-1,-1,1}, new double[]{1,1,-1,-1}, 1, 0);
		Assert.assertTrue("Overlapping point and 225 degree rectangle", p1.getHitBox().collideWith(r1.getHitBox()));
		p1 = new PointEntity(10, 10, 0, 1);
		r1 = new RectEntity(0, 0, 225, new double[]{1,-1,-1,1}, new double[]{1,1,-1,-1}, 1, 0);
		Assert.assertFalse("Non-overlapping point and 225 degree rectangle", p1.getHitBox().collideWith(r1.getHitBox()));
		
		p1 = new PointEntity(0, 0, 0, 1);
		r1 = new RectEntity(0, 0, 270, new double[]{1,-1,-1,1}, new double[]{1,1,-1,-1}, 1, 0);
		Assert.assertTrue("Overlapping point and 270 degree rectangle", p1.getHitBox().collideWith(r1.getHitBox()));
		p1 = new PointEntity(10, 10, 0, 1);
		r1 = new RectEntity(0, 0, 270, new double[]{1,-1,-1,1}, new double[]{1,1,-1,-1}, 1, 0);
		Assert.assertFalse("Non-overlapping point and 270 degree rectangle", p1.getHitBox().collideWith(r1.getHitBox()));
		
		p1 = new PointEntity(0, 0, 0, 1);
		r1 = new RectEntity(0, 0, 315, new double[]{1,-1,-1,1}, new double[]{1,1,-1,-1}, 1, 0);
		Assert.assertTrue("Overlapping point and 315 degree rectangle", p1.getHitBox().collideWith(r1.getHitBox()));
		p1 = new PointEntity(10, 10, 0, 1);
		r1 = new RectEntity(0, 0, 315, new double[]{1,-1,-1,1}, new double[]{1,1,-1,-1}, 1, 0);
		Assert.assertFalse("Non-overlapping point and 315 degree rectangle", p1.getHitBox().collideWith(r1.getHitBox()));
		
		p1 = new PointEntity(0, 0, 0, 1);
		r1 = new RectEntity(0, 0, 360, new double[]{1,-1,-1,1}, new double[]{1,1,-1,-1}, 1, 0);
		Assert.assertTrue("Overlapping point and 90 degree rectangle", p1.getHitBox().collideWith(r1.getHitBox()));
		p1 = new PointEntity(10, 10, 0, 1);
		r1 = new RectEntity(0, 0, 360, new double[]{1,-1,-1,1}, new double[]{1,1,-1,-1}, 1, 0);
		Assert.assertFalse("Non-overlapping point and 90 degree rectangle", p1.getHitBox().collideWith(r1.getHitBox()));
		
		p1 = new PointEntity(1.1, 0, 0, 1);
		r1 = new RectEntity(0, 0, 45, new double[]{1,-1,-1,1}, new double[]{1,1,-1,-1}, 1, 0);
		Assert.assertTrue("Overlapping point and 45 degree rectangle, rotation dependent", p1.getHitBox().collideWith(r1.getHitBox()));
		p1 = new PointEntity(1, 1, 0, 1);
		r1 = new RectEntity(0, 0, 45, new double[]{1,-1,-1,1}, new double[]{1,1,-1,-1}, 1, 0);
		Assert.assertFalse("Non-overlapping point and 45 degree rectangle, rotation dependent", p1.getHitBox().collideWith(r1.getHitBox()));
	}
	
	@Test
	public void testPC() {
		PointEntity p1 = new PointEntity(0, 0, 0, 1);
		CircleEntity c1 = new CircleEntity(0, 0, 5, 1, 0);
		Assert.assertTrue("Point at center of circle", p1.getHitBox().collideWith(c1.getHitBox()));

		p1 = new PointEntity(1, 1, 0, 1);
		Assert.assertTrue("Point inside circle", p1.getHitBox().collideWith(c1.getHitBox()));

		p1 = new PointEntity(10, 10, 0, 1);
		Assert.assertFalse("Point outside circle", p1.getHitBox().collideWith(c1.getHitBox()));
	}
	
	@Test
	public void testRR() {
		
	}
	
	@Test
	public void testRC() {
		
	}
	
	@Test
	public void testCC() {
		CircleEntity c1 = new CircleEntity(0, 0, 5, 0, 1);
		CircleEntity c2 = new CircleEntity(0, 0, 5, 1, 0);
		Assert.assertTrue("Identical circles", c1.getHitBox().collideWith(c2.getHitBox()));
		
		c2 = new CircleEntity(3, 3, 5, 1, 0);
		Assert.assertTrue("Overlapping circles", c1.getHitBox().collideWith(c2.getHitBox()));
		
		c2 = new CircleEntity(20, 20, 5, 1, 0);
		Assert.assertFalse("Non-overlapping circles", c1.getHitBox().collideWith(c2.getHitBox()));
	}
	
	abstract class TestEntity<H extends HitBox> extends Entity<H>{
		boolean colliding = false;
		@Override
		public void init(){}	
		@Override
		public void update(Scene scene){}
		@Override
		public void onCollision(Scene scene, Entity<?> e) {
			colliding = true;
		}
		@Override
		public void draw(Graphics2D g){}
	}
	
	class PointEntity extends TestEntity<PointHitBox>{
		public PointEntity(double x, double y, int colType, int... colTargets){
			this.x = x;
			this.y = y;
			hitBox = new PointHitBox(this);
			collisionType = colType;
			collisionTargets = colTargets;
		}
	}
	
	class RectEntity extends TestEntity<RectHitBox>{
		public RectEntity(double x, double y, double angle, double[] xCorners, double[] yCorners, int colType, int... colTargets){
			this.x = x;
			this.y = y;
			this.angle = angle;
			hitBox = new RectHitBox(this, xCorners, yCorners);
			collisionType = colType;
			collisionTargets = colTargets;
		}
	}
	
	class CircleEntity extends TestEntity<CircleHitBox>{
		public CircleEntity(double x, double y, int r, int colType, int... colTargets){
			this.x = x;
			this.y = y;
			hitBox = new CircleHitBox(this, r);
			collisionType = colType;
			collisionTargets = colTargets;
		}
	}

}
