package org.readbook.activity;

import java.util.ArrayList;
import java.util.List;

import org.readbook.R;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

public class GuideActivity extends BaseActivity {

	private List<View> viewList = new ArrayList<View>();
	private LayoutInflater inflater;
	private ViewPager vp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(0);
		initView();
	}
	
	
	private void initView() {
		vp = (ViewPager) findViewById(0);
		inflater = getLayoutInflater();

		View view1 = inflater.inflate(0, null);
		((ImageView) view1.findViewById(0)).setImageResource(0);
		View view2 = inflater.inflate(0, null);
		((ImageView) view2.findViewById(0)).setImageResource(0);
		View view3 = inflater.inflate(0, null);
		((ImageView) view3.findViewById(0)).setImageResource(0);
		viewList.add(view1);
		viewList.add(view2);
		viewList.add(view3);
		view3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		vp.setAdapter(new MyPagerAdapter());
		
		vp.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int arg0) {
//				if(arg0 == 0){
//					loginLayout.setVisibility(View.GONE);
//					indexImage.setBackgroundResource(R.drawable.guide_index1);
//				}else if(arg0 == 1){
//					loginLayout.setVisibility(View.GONE);
//					indexImage.setBackgroundResource(R.drawable.guide_index2);
//				}else if(arg0 == 2){
//					loginLayout.setVisibility(View.VISIBLE);
//					indexImage.setBackgroundResource(R.drawable.guide_index3);
//				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});
	}

	private class MyPagerAdapter extends PagerAdapter {

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(viewList.get(position));
			return viewList.get(position);
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(viewList.get(position));
		}

		@Override
		public int getCount() {
			return viewList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

	}

}
