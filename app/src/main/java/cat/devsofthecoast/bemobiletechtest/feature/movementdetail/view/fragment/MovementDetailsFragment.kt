package cat.devsofthecoast.bemobiletechtest.feature.movementdetail.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import cat.devsofthecoast.bemobiletechtest.databinding.FragmentMovementDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovementDetailsFragment : Fragment() {

    val args: MovementDetailsFragmentArgs by navArgs()

    lateinit var binding: FragmentMovementDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovementDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()
        binding.tvMain.text = args.details.name
    }

    private fun setUpObservers() {
        // no op
    }

}