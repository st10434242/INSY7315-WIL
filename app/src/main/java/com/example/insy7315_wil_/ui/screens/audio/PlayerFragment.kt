package com.example.insy7315_wil_.ui.screens.audio

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.insy7315_wil_.databinding.FragmentPlayerBinding
import com.example.insy7315_wil_.ui.screens.quiz.ARG_PLAYER_SUBTITLE
import com.example.insy7315_wil_.ui.screens.quiz.ARG_PLAYER_TITLE
import com.example.insy7315_wil_.ui.screens.quiz.ARG_POINTS_EARNED
import com.example.insy7315_wil_.ui.screens.quiz.ARG_RECOMMENDED_BROADCAST
import com.example.insy7315_wil_.ui.screens.quiz.ARG_RECOMMENDED_CATEGORY
import com.example.insy7315_wil_.ui.screens.quiz.ARG_SELECTED_ANSWER
import com.example.insy7315_wil_.R

class PlayerFragment : Fragment(R.layout.fragment_player) {

    private var _binding: FragmentPlayerBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPlayerBinding.bind(view)

        val args = requireArguments()
        val answer = args.getString(ARG_SELECTED_ANSWER).orEmpty()
        val category = args.getString(ARG_RECOMMENDED_CATEGORY).orEmpty()
        val broadcast = args.getString(ARG_RECOMMENDED_BROADCAST).orEmpty()
        val playerTitle = args.getString(ARG_PLAYER_TITLE).orEmpty()
        val playerSubtitle = args.getString(ARG_PLAYER_SUBTITLE).orEmpty()
        val points = args.getInt(ARG_POINTS_EARNED, 15)
        val resolvedTitle = if (playerTitle.isNotBlank()) playerTitle else "Calm reset"
        val resolvedSubtitle =
            if (playerSubtitle.isNotBlank()) playerSubtitle else "David's soft breath broadcast"

        binding.playerRecommendationSubtitle.text =
            "Your matched meditation is loaded. You earned +$points points."
        binding.playerCategoryLabel.text =
            if (category.isNotBlank()) "Meditation category: $category" else "Meditation category: Calm reset"
        binding.playerBroadcastLabel.text =
            if (broadcast.isNotBlank()) "Broadcast: $broadcast" else "Broadcast: David's soft breath broadcast"
        binding.playerAnswerLabel.text =
            if (answer.isNotBlank()) "Matched from: $answer" else "Matched from: Calm"

        binding.playerBar.setTitle(resolvedTitle)
        binding.playerBar.setSubtitle(resolvedSubtitle)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
