package com.kelin.mvvmlight.bindingadapter.recyclerview;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.kelin.mvvmlight.command.ReplyCommand;

import java.util.concurrent.TimeUnit;

import rx.subjects.PublishSubject;

/**
 * Created by kelin on 16-4-26.
 */
@SuppressWarnings("ResultOfMethodCallIgnored")
public class ViewBindingAdapter {

    @SuppressWarnings("unused")
    @BindingAdapter(value = {"onScrollChangeCommand", "onScrollStateChangedCommand"}, requireAll = false)
    public static void onScrollChangeCommand(final RecyclerView recyclerView,
                                             final ReplyCommand<ScrollDataWrapper> onScrollChangeCommand,
                                             final ReplyCommand<Integer> onScrollStateChangedCommand) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int state;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (onScrollChangeCommand != null) {
                    onScrollChangeCommand.execute(new ScrollDataWrapper(dx, dy, state));
                }
            }

            @SuppressWarnings("EqualsBetweenInconvertibleTypes")
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                state = newState;
                if (onScrollStateChangedCommand != null) {
                    onScrollChangeCommand.equals(newState);
                }
            }
        });

    }

    @SuppressWarnings("unchecked")
    @BindingAdapter(value = {"onLoadMoreCommand","onTopLoadMoreCommand"}, requireAll = false)
    public static void onLoadMoreCommand(final RecyclerView recyclerView, final ReplyCommand<Integer> onLoadMoreCommand,final ReplyCommand<Integer> onTopLoadMoreCommand) {
        RecyclerView.OnScrollListener listener = new OnScrollListener(onLoadMoreCommand,onTopLoadMoreCommand);
        recyclerView.addOnScrollListener(listener);

    }

    public static class OnScrollListener extends RecyclerView.OnScrollListener {

        private PublishSubject<Integer> methodInvoke = PublishSubject.create();
        private PublishSubject<Integer> topMethodInvoke = PublishSubject.create();

        private ReplyCommand<Integer> onLoadMoreCommand;
        private ReplyCommand<Integer> onTopLoadMoreCommand;

        public OnScrollListener(ReplyCommand<Integer> onLoadMoreCommand,ReplyCommand<Integer> onTopLoadMoreCommand) {
            this.onLoadMoreCommand = onLoadMoreCommand;
            this.onTopLoadMoreCommand = onTopLoadMoreCommand;
            if(onLoadMoreCommand != null){
                methodInvoke.throttleFirst(1, TimeUnit.SECONDS)
                        .subscribe(onLoadMoreCommand::execute);
            }
            if(onTopLoadMoreCommand != null){
                topMethodInvoke.throttleFirst(1, TimeUnit.SECONDS)
                        .subscribe(onTopLoadMoreCommand::execute);
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//            LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
//            int visibleItemCount = layoutManager.getChildCount();
//            int totalItemCount = layoutManager.getItemCount();
//            int pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();
//            if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
//                if (onLoadMoreCommand != null) {
//                    methodInvoke.onNext(recyclerView.getAdapter().getItemCount());
//                }
//            }

            if (!recyclerView.canScrollVertically(-1)) {
                if (onTopLoadMoreCommand != null) {
                    topMethodInvoke.onNext(recyclerView.getAdapter().getItemCount());
                }
            } else if (!recyclerView.canScrollVertically(1)) {
                if (onLoadMoreCommand != null) {
                    methodInvoke.onNext(recyclerView.getAdapter().getItemCount());
                }
            }
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }


    }

    public static class ScrollDataWrapper {
        @SuppressWarnings("unused")
        public float scrollX;
        @SuppressWarnings("unused")
        public float scrollY;
        @SuppressWarnings("unused")
        public int state;

        public ScrollDataWrapper(float scrollX, float scrollY, int state) {
            this.scrollX = scrollX;
            this.scrollY = scrollY;
            this.state = state;
        }
    }
}
