package com.puc.pos_mobile.bmicalculator

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.puc.pos_mobile.bmicalculator.MainActivity
import com.puc.pos_mobile.bmicalculator.R
import com.puc.pos_mobile.bmicalculator.databinding.ActivityResultadoBinding

class ResultadoActivity : AppCompatActivity() {

    private lateinit var binding : ActivityResultadoBinding
    val i = intent
    val idade = i.getIntExtra(MainActivity.IDADE, 0)
    val resultado = i.getDoubleExtra(MainActivity.RESULTADO, 0.00)

    var valorIMC = ""
    var informacao = ""
//    var imagem = ImageView(this);
    var backColor = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
    }

    private fun setupView(){

        if (idade.equals(1) ) {
//            imagem.setImageDrawable(R.drawable.adultos)
            if (resultado <= 18.5) {
                valorIMC = resultado.toString()
                informacao = "Você está abaixo do peso ideal. Isso pode ser apenas uma característica pessoal, mas também pode ser um sinal de\n" +
                        "desnutrição ou de algum problema de saúde. Caso esteja perdendo peso sem motivo aparente, procure um médico."
                backColor = getColor(R.color.amarelo)
            } else if (resultado > 18.5 && resultado <= 24.9) {
                valorIMC = resultado.toString()
                informacao = "Você está no peso ideal. Parabéns! Mantenha seus hábitos alimentares e cuidados com a " +
                        "saúde"
                backColor = getColor(R.color.azul)
            } else if (resultado >= 25.0 && resultado <= 29.9) {
                valorIMC = resultado.toString()
                informacao = "Atenção! Alguns quilos a mais já são suficientes para que algumas pessoas desenvolvam doenças associadas, como\n" +
                        "diabetes e hipertensão. É importante rever seus hábitos. Procure um médico."
                backColor = getColor(R.color.amarelo)
            } else if (resultado >= 30.0 && resultado <= 34.9) {
                valorIMC = resultado.toString()
                informacao = "Sinal de alerta! O excesso de peso é fator de risco para desenvolvimento de outros problemas de saúde. A boa notícia\n" +
                        "é que uma pequena perda de peso já traz benefícios à saúde. Procure um médico para definir o tratamento mais adequado\n" +
                        "para você"
                backColor = getColor(R.color.vermelho)
            } else if (resultado >= 35.0 && resultado <= 29.9) {
                valorIMC = resultado.toString()
                informacao = "Sinal vermelho! Ao atingir este nível de IMC, o risco de doenças associadas está entre alto e muito alto. Busque\n" +
                        "ajuda de um profissional de saúde; não perca tempo."
                getColor(R.color.vermelho)
            } else if (resultado >= 40.0) {
                valorIMC = resultado.toString()
                informacao = "Sinal vermelho! Ao atingir este nível de IMC, o risco de doenças associadas é muito alto. Busque ajuda de um\n" +
                        "profissional de saúde; não perca tempo."
                backColor = getColor(R.color.vermelho)
            }
        } else if (idade.equals(2)){
//            imagem.setImageDrawable(R.drawable.idosos)
            if (resultado < 22) {
                valorIMC = resultado.toString()
                informacao = "Você está abaixo do peso ideal. Isso pode ser apenas uma característica pessoal, mas também pode ser um sinal de\n" +
                        "desnutrição ou de algum problema de saúde. Caso esteja perdendo peso sem motivo aparente, procure um médico."
                backColor = getColor(R.color.amarelo)
            } else if (resultado > 22.0 && resultado < 27.0) {
                valorIMC = resultado.toString()
                informacao = "Você está no peso ideal. Parabéns! Mantenha seus hábitos alimentares e cuidados com a " +
                        "saúde"
                backColor = getColor(R.color.azul)
            } else if (resultado >= 27.0) {
                valorIMC = resultado.toString()
                informacao = "Sinal de alerta! O excesso de peso é fator de risco para desenvolvimento de outros problemas de saúde. A boa notícia\n" +
                        "é que uma pequena perda de peso já traz benefícios à saúde. Procure um médico para definir o tratamento mais adequado\n" +
                        "para você"
                backColor = getColor(R.color.vermelho)
            }
        }

        binding.informacao.text = informacao
        binding.valorIMC.setTextColor(backColor)
        binding.classificacao.setTextColor(backColor)
        binding.valorIMC.text = valorIMC
    }
}