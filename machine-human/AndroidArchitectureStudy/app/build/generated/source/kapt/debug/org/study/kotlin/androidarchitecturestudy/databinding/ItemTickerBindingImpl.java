package org.study.kotlin.androidarchitecturestudy.databinding;
import org.study.kotlin.androidarchitecturestudy.R;
import org.study.kotlin.androidarchitecturestudy.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ItemTickerBindingImpl extends ItemTickerBinding  {

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

    public ItemTickerBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }
    private ItemTickerBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[1]
            , (android.widget.TextView) bindings[2]
            );
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.textviewItemAccTradePrice24h.setTag(null);
        this.textviewItemChangeRate.setTag(null);
        this.textviewItemMarket.setTag(null);
        this.textviewItemTradePrice.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
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
            setVm((org.study.kotlin.androidarchitecturestudy.api.model.TickerModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setVm(@Nullable org.study.kotlin.androidarchitecturestudy.api.model.TickerModel Vm) {
        this.mVm = Vm;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.vm);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
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
        java.lang.String vmConvertAccTradePrice24h = null;
        java.lang.String stringValueOfVmConvertMarket = null;
        java.lang.String vmConvertChangeRate = null;
        java.lang.String stringValueOfVmConvertTradePrice = null;
        java.lang.String vmConvertTradePrice = null;
        org.study.kotlin.androidarchitecturestudy.api.model.TickerModel vm = mVm;
        java.lang.String vmConvertMarket = null;
        java.lang.String stringValueOfVmConvertChangeRate = null;
        java.lang.String stringValueOfVmConvertAccTradePrice24h = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (vm != null) {
                    // read vm.convertAccTradePrice24h
                    vmConvertAccTradePrice24h = vm.getConvertAccTradePrice24h();
                    // read vm.convertChangeRate
                    vmConvertChangeRate = vm.getConvertChangeRate();
                    // read vm.convertTradePrice
                    vmConvertTradePrice = vm.getConvertTradePrice();
                    // read vm.convertMarket
                    vmConvertMarket = vm.getConvertMarket();
                }


                // read String.valueOf(vm.convertAccTradePrice24h)
                stringValueOfVmConvertAccTradePrice24h = java.lang.String.valueOf(vmConvertAccTradePrice24h);
                // read String.valueOf(vm.convertChangeRate)
                stringValueOfVmConvertChangeRate = java.lang.String.valueOf(vmConvertChangeRate);
                // read String.valueOf(vm.convertTradePrice)
                stringValueOfVmConvertTradePrice = java.lang.String.valueOf(vmConvertTradePrice);
                // read String.valueOf(vm.convertMarket)
                stringValueOfVmConvertMarket = java.lang.String.valueOf(vmConvertMarket);
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            this.textviewItemAccTradePrice24h.setText(stringValueOfVmConvertAccTradePrice24h);
            this.textviewItemChangeRate.setText(stringValueOfVmConvertChangeRate);
            this.textviewItemMarket.setText(stringValueOfVmConvertMarket);
            this.textviewItemTradePrice.setText(stringValueOfVmConvertTradePrice);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): vm
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}