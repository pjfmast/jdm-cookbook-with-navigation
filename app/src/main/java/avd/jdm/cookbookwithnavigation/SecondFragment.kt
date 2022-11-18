package avd.jdm.cookbookwithnavigation

import android.os.Bundle
import android.view.*
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import avd.jdm.cookbookwithnavigation.databinding.FragmentSecondBinding
import androidx.navigation.fragment.navArgs
import avd.jdm.cookbookwithnavigation.model.Recipe
import avd.jdm.cookbookwithnavigation.model.RecipeDaoImpl


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private val args: SecondFragmentArgs by navArgs()
    private val recipes = RecipeDaoImpl()
    private val recipe: Recipe? = recipes.find(args.recipe)

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpMenu()

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        binding.textviewSecond.text =
            recipe?.toString() ?: "no recipe found with name ${args.recipe}"
    }

    private fun setUpMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_recipe_details, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_share -> {
                        shareSelectedRecipe()
                        true
                    }
                    else -> false
                   // false indicates menu item not handled,
                   // see: https://stackoverflow.com/questions/23170715/android-menuitem-onclick-handlers-return-value
                }
            }
        },  // problem duplicated menu-items is solved with adding state
            // – the Lifecycle.State to check for automated addition/removal
            viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun shareSelectedRecipe() {
        // example of the use of the builder pattern:
        val shareByMailIntent = ShareCompat.IntentBuilder(requireContext())
            .setType("text/plain")
            .setSubject("My menu choice for today")
            .setText("Hi, today I'm having this delicious meal for dinner: $recipe")
            .intent

        startActivity(shareByMailIntent)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}