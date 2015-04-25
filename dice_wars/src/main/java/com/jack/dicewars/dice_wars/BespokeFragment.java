package com.jack.dicewars.dice_wars;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;


/**
 * A simple {@link DialogFragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link com.jack.dicewars.dice_wars.BespokeFragment.OnBespokeActionListener} interface
 * to handle interaction events.
 * Use the {@link BespokeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BespokeFragment extends DialogFragment {

    private OnBespokeActionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment BespokeFragment.
     */
    public static BespokeFragment newInstance() {
        BespokeFragment fragment = new BespokeFragment();
        return fragment;
    }

    /**
     * Empty constructor for Fragment.
     */
    public BespokeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.f_bespoke, container, true);
    }

    /**
     *
     * @return The flags defined by Debug indicated by the checked radio buttons.
     */
    public int confirmBespoke() {
        int bespokeFlag = 0;

        final RadioGroup startStateGroup = (RadioGroup) getActivity().findViewById(R.id.radioGroupStart);
        switch (startStateGroup.getCheckedRadioButtonId()) {
            case R.id.radioEasyWin:
                bespokeFlag |= Debug.easyWin.f;
                break;
            default:
                break;
        }

        final RadioGroup boardTypeGroup = (RadioGroup) getActivity().findViewById(R.id.radioGroupBoard);
        switch (boardTypeGroup.getCheckedRadioButtonId()) {
            case R.id.radioGridTextBoard:
                bespokeFlag |= Debug.gridText.f;
                break;
            default:
                break;
        }
        return bespokeFlag;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnBespokeActionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnBespokeActionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnBespokeActionListener {
        /**
         *
         * @param view the button clicked to call this function.
         */
        void onConfirmBespoke(View view);
    }

}
