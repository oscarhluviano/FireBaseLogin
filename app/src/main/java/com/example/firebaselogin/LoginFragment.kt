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
import com.google.android.material.textview.MaterialTextView
import com.google.firebase.auth.FirebaseAuth

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
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
        // Inflate the layout for this fragment
        autenticacion = FirebaseAuth.getInstance()
        return inflater.inflate(R.layout.fragment_login, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<MaterialTextView>(R.id.btnLoginSignup).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_signupFragment)
        }

        view.findViewById<Button>(R.id.btnLogin).setOnClickListener {
            login(view)
        }
    }

    fun login(view: View){
        val correo: String = view.findViewById<EditText>(R.id.etUser).text.toString()
        val password: String = view.findViewById<EditText>(R.id.etPswd).text.toString()

        if (correo.isEmpty()){
            view.findViewById<EditText>(R.id.etUser).requestFocus()
            view.findViewById<EditText>(R.id.etUser).error = "Faltan datos"
        }else if(password.isEmpty()){
            view.findViewById<EditText>(R.id.etPswd).requestFocus()
            view.findViewById<EditText>(R.id.etPswd).error = "Faltan datos"
        }else{
            autenticacion.signInWithEmailAndPassword(correo,password).addOnCompleteListener {
                if (it.isSuccessful){
                    //avanzar a la pantalla principal
                    Toast.makeText(this.context, "Exito", Toast.LENGTH_SHORT).show()
                    Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_initialFragment)
                }else{
                    //Mostrar un resultado error
                    Toast.makeText(this.context, "Error", Toast.LENGTH_SHORT).show()
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
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}