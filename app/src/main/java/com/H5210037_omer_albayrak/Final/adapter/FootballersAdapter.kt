package com.H5210037_omer_albayrak.Final.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.H5210037_omer_albayrak.Final.databinding.FootballersViewBinding
import com.H5210037_omer_albayrak.Final.databinding.GridFootballersViewBinding
import com.H5210037_omer_albayrak.Final.model.Footballer
import com.H5210037_omer_albayrak.Final.ui.menu.MainActivity
import com.H5210037_omer_albayrak.Final.util.getFromUrl

//Class tanımlanma sebebi
//Bu class recyclerView için bir adaptör oluşturmaktadır. Bu adaptör sayesinde recyclerView'in aldığı veriyi bir arayüz görüntüsüne basarız ve kullanıcıya gösterebiliriz.

class FootballersAdapter(
    var footballers: ArrayList<Footballer>, //Bu bir arraylist alır futbolcular verisini basabilmesi için
    var view: MainActivity.LIST_TYPE, //Buradaki liste tipi sayesinde recyclerview tipini istediğimiz gibi değiştiririz
    var onClick: (Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    //Buradaki iki inner class sayesinde istediğimiz arayüzView'ine bağlantı kurarız binding ile. iki farklı viewholder olduğu için iki farklı inner class var

    inner class FootballerViewHolder(val binding: FootballersViewBinding) :
        RecyclerView.ViewHolder(binding.root)

    inner class GridViewHolder(val binding: GridFootballersViewBinding) :
        RecyclerView.ViewHolder(binding.root)

    //Viewholderi burada oluştururuz ve bir if şartı sayesinde kullanıcının seçtiği tipe göre bir viewholder döndürürüz

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (view == MainActivity.LIST_TYPE.LIST) {
            val binding = FootballersViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return FootballerViewHolder(binding)
        } else {
            val binding = GridFootballersViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return GridViewHolder(binding)
        }
    }

    //Burada listenin size'ini alırız ve bu sayede recyclerview'in kaç tane item basacağını gösteririz

    override fun getItemCount(): Int {
        return footballers.size
    }

    //Burada binding oluşmuş olur ve bu sayede binding ile verilerimizi arayüze basarız.

    //Ayrıca when şartı ile arayüzümüzün gridview mi yoksa listeview mi olduğunu anlarız ve ona göre bir arayüz döndürürüz.

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FootballerViewHolder -> {
                val footballerViewHolder = holder as FootballerViewHolder
                val binding = footballerViewHolder.binding
                binding.txtName.text = footballers[position].footballerName
                binding.txtInformation.text = footballers[position].footballerInformation
                binding.imageView.getFromUrl(footballers[position].url!!)
                binding.cardViewFootballers.setOnClickListener {
                    onClick(position)
                }
            }
            is GridViewHolder -> {
                val gridViewHolder = holder as GridViewHolder
                val binding = gridViewHolder.binding
                binding.txtName.text = footballers[position].footballerName
                binding.txtInformation.text = footballers[position].footballerInformation
                binding.imageView.getFromUrl(footballers[position].url!!)
                binding.cardViewFootballers.setOnClickListener {
                    onClick(position)
                }
            }
        }
    }
}