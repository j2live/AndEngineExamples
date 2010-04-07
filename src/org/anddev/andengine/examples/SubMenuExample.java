package org.anddev.andengine.examples;

import org.anddev.andengine.entity.menu.MenuItem;
import org.anddev.andengine.entity.menu.MenuScene;
import org.anddev.andengine.entity.menu.animator.SlideMenuAnimator;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureManager;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;


/**
 * @author Nicolas Gramlich
 * @since 11:33:33 - 01.04.2010
 */
public class SubMenuExample extends MenuExample {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final int MENU_QUIT_OK = MenuExample.MENU_QUIT + 1;
	private static final int MENU_QUIT_BACK = MENU_QUIT_OK + 1;

	// ===========================================================
	// Fields
	// ===========================================================
	
	private MenuScene mSubMenuScene;

	private Texture mSubMenuTexture;
	private TextureRegion mMenuOkTextureRegion;
	private TextureRegion mMenuBackTextureRegion;

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void onLoadResources() {
		super.onLoadResources();
		this.mSubMenuTexture = new Texture(256, 128);

		this.mMenuOkTextureRegion = TextureRegionFactory.createFromAsset(this.mSubMenuTexture, this, "gfx/menu_ok.png", 0, 0);
		this.mMenuBackTextureRegion = TextureRegionFactory.createFromAsset(this.mSubMenuTexture, this, "gfx/menu_back.png", 0, 50);

		TextureManager.loadTexture(this.mSubMenuTexture);
	}

	@Override
	protected MenuScene createMenuScene() {
		final MenuScene mainMenuScene = super.createMenuScene();

		this.mSubMenuScene = new MenuScene(this.mCamera);
		this.mSubMenuScene.addMenuItem(new MenuItem(MENU_QUIT_OK, this.mMenuOkTextureRegion));
		this.mSubMenuScene.addMenuItem(new MenuItem(MENU_QUIT_BACK, this.mMenuBackTextureRegion));
		this.mSubMenuScene.setMenuAnimator(new SlideMenuAnimator());
		this.mSubMenuScene.buildAnimations();

		this.mSubMenuScene.setBackgroundEnabled(false);

		this.mSubMenuScene.setOnMenuItemClickerListener(this);

		return mainMenuScene;
	}

	@Override
	public void onMenuItemClicked(final MenuScene pMenuScene, final MenuItem pMenuItem) {
		switch(pMenuItem.getMenuID()) {
			case MENU_RESET:
				this.mMainScene.reset();
				
				this.mMenuScene.back();
				break;
			case MENU_QUIT:
				pMenuScene.setChildSceneModal(this.mSubMenuScene);
				break;
			case MENU_QUIT_BACK:
				this.mSubMenuScene.back();
				break;
			case MENU_QUIT_OK:
				this.finish();
				break;
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}