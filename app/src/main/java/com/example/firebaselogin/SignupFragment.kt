package com.example.firebaselogin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SignupFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignupFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var autenticacion: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        autenticacion = FirebaseAuth.getInstance()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.btnSignup).setOnClickListener {
            signup(view)
        }
    }

    fun signup(view: View){
        val mail: String = view.findViewById<EditText>(R.id.etEmail).text.toString()
        val password: String = view.findViewById<EditText>(R.id.etPassword).text.toString()

        if (mail.isEmpty()){
            view.findViewById<EditText>(R.id.etEmail).requestFocus()
            view.findViewById<EditText>(R.id.etEmail).error = "Faltan datos"
        }else if(password.isEmpty()){
            view.findViewById<EditText>(R.id.etPassword).requestFocus()
            view.findViewById<EditText>(R.id.etPassword).error = "Faltan datos"
        }else{
            autenticacion.createUserWithEmailAndPassword(mail, password).addOnCompleteListener {
                if (it.isSuccessful){
                    Navigation.findNavController(view).navigate(R.id.action_signupFragment_to_loginFragment)
                }else{
                    Toast.makeText(this.context, "Algo fall??", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SignupFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SignupFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}