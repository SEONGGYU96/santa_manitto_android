package org.sopt.santamanitto.room.manittoroom.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.santamanitto.R
import org.sopt.santamanitto.databinding.FragmentManittoRoomFinishBinding
import org.sopt.santamanitto.room.manittoroom.ManittoRoomViewModel
import org.sopt.santamanitto.view.setLayoutHeight

@AndroidEntryPoint
class FinishFragment: Fragment() {

    private lateinit var binding: FragmentManittoRoomFinishBinding

    private val manittoRoomViewModel: ManittoRoomViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentManittoRoomFinishBinding.inflate(inflater, container, false)
            .apply {
                lifecycleOwner = viewLifecycleOwner
                viewModel = manittoRoomViewModel
                root.addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
                    override fun onLayoutChange(v: View, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int,
                                                oldTop: Int, oldRight: Int, oldBottom: Int) {
                        binding.root.removeOnLayoutChangeListener(this)

                        initMissionText()
                    }
                })
            }

        manittoRoomViewModel.run {
            refreshManittoRoomInfo()
            getPersonalRelationInfo()
        }

        setOnClickListener()

        return binding.root
    }

    private fun setOnClickListener() {
        binding.run {
            santabottombuttonFinish.setOnClickListener {
                navigateResultFragment()
            }
            santabackgroundFinish.setOnBackKeyClickListener {
                requireActivity().finish()
            }
        }
    }

    private fun navigateResultFragment() {
        findNavController().navigate(FinishFragmentDirections.actionFinishFragmentToResultFragment())
    }

    private fun initMissionText() {
        binding.textviewFinishMission.run {
            setLayoutHeight(this, this.height)
            manittoRoomViewModel.missionToMe.observe(viewLifecycleOwner) {
                text = if (it.isNullOrEmpty()) {
                    getString(R.string.matched_no_mission)
                } else {
                    it
                }
            }
        }
    }
}