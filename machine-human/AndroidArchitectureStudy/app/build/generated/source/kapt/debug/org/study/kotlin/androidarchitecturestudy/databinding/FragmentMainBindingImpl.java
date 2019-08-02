package org.study.kotlin.androidarchitecturestudy.databinding;
import org.study.kotlin.androidarchitecturestudy.R;
import org.study.kotlin.androidarchitecturestudy.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentMainBindingImpl extends FragmentMainBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = null;
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentMainBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 2, sIncludes, sViewsWithIds));
    }
    private FragmentMainBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (androidx.recyclerview.widget.RecyclerView) bindings[1]
            );
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.recyclerviewMainfragment.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x4L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.vm == variableId) {
            setVm((org.study.kotlin.androidarchitecturestudy.view.activity.main.MainViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setVm(@Nullable org.study.kotlin.androidarchitecturestudy.view.activity.main.MainViewModel Vm) {
        this.mVm = Vm;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.vm);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeVmObservableTickerList((androidx.lifecycle.LiveData<java.util.List<org.study.kotlin.androidarchitecturestudy.api.model.TickerModel>>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeVmObservableTickerList(androidx.lifecycle.LiveData<java.util.List<org.study.kotlin.androidarchitecturestudy.api.model.TickerModel>> VmObservableTickerList, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        org.study.kotlin.androidarchitecturestudy.view.activity.main.MainViewModel vm = mVm;
        java.util.List<org.study.kotlin.androidarchitecturestudy.api.model.TickerModel> vmObservableTickerListGetValue = null;
        androidx.lifecycle.LiveData<java.util.List<org.study.kotlin.androidarchitecturestudy.api.model.TickerModel>> vmObservableTickerList = null;

        if ((dirtyFlags & 0x7L) != 0) {



                if (vm != null) {
                    // read vm.observableTickerList
                    vmObservableTickerList = vm.getObservableTickerList();
                }
                updateLiveDataRegistration(0, vmObservableTickerList);


                if (vmObservableTickerList != null) {
                    // read vm.observableTickerList.getValue()
                    vmObservableTickerListGetValue = vmObservableTickerList.getValue();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x7L) != 0) {
            // api target 1

            org.study.kotlin.androidarchitecturestudy.ext.BaseRecyclerViewExtKt.replaceAll(this.recyclerviewMainfragment, vmObservableTickerListGetValue);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): vm.observableTickerList
        flag 1 (0x2L): vm
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}