package br.com.luanadev.mysubscribers.ui.subscriber

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.luanadev.mysubcribers.R
import br.com.luanadev.mysubscribers.data.db.AppDatabase
import br.com.luanadev.mysubscribers.data.db.dao.SubscriberDao
import br.com.luanadev.mysubscribers.extention.hideKeyboard
import br.com.luanadev.mysubscribers.repository.DatabaseDataSource
import br.com.luanadev.mysubscribers.repository.SubscriberRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.subscriber_fragment.*

class SubscriberFragment : Fragment(R.layout.subscriber_fragment) {

    private val viewModel: SubscriberViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val subscriberDao: SubscriberDao =
                    AppDatabase.getInstance(requireContext()).subscriberDao
                val repository: SubscriberRepository =
                    DatabaseDataSource(subscriberDao = subscriberDao)
                return SubscriberViewModel(repository = repository) as T
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeEvents()
        setListeners()
    }

    private fun observeEvents() {
        viewModel.subscriberStateEventData.observe(viewLifecycleOwner) { subscriberState ->
            when (subscriberState) {
                is SubscriberViewModel.SubscriberState.Inserted -> {
                    clearFields()
                    hideKeyboard()
                }
            }
        }
        viewModel.messageEventLiveData.observe(viewLifecycleOwner) { stringResId ->
            Snackbar.make(requireView(), stringResId, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun clearFields() {
        input_name.text?.clear()
        input_email.text?.clear()
    }

    private fun hideKeyboard(){
        val parentActivity = requireActivity()
        if(parentActivity is AppCompatActivity){
            parentActivity.hideKeyboard()
        }
    }

    private fun setListeners() {
        button_subscriber.setOnClickListener{
            val name = input_name.text.toString()
            val email = input_email.text.toString()

            viewModel.addSubscriber(name, email)
        }
    }
}