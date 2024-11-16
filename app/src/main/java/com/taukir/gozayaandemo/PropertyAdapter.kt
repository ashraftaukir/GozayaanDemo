import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.taukir.gozayaandemo.Property
import com.taukir.gozayaandemo.R

class PropertyAdapter(private val properties: List<Property>) :
    RecyclerView.Adapter<PropertyAdapter.PropertyViewHolder>() {

    class PropertyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val propertyName: TextView = itemView.findViewById(R.id.textViewPropertyName)
        val location: TextView = itemView.findViewById(R.id.textViewLocation)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_property, parent, false)
        return PropertyViewHolder(view)
    }

    override fun onBindViewHolder(holder: PropertyViewHolder, position: Int) {
        val property = properties[position]

        // Set property name and location
        holder.propertyName.text = property.property_name
        holder.location.text = property.location

        // Load the hero image using Glide
        Glide.with(holder.itemView.context)
            .load(property.hero_image)
            .placeholder(R.drawable.ic_hotel)
            .error(R.mipmap.ic_launcher_round) // Replace with your error image resource

            .into(holder.imageView)

    }

    override fun getItemCount(): Int = properties.size
}
