package com.example.oasis_hackathon_app.methods;

import com.example.oasis_hackathon_app.R;

public class CategoryBtn {
    String category;
    int[] images = {R.drawable.category_vegetable,
                    R.drawable.category_fruit,
                    R.drawable.category_beverage,
                    R.drawable.category_salad,
                    R.drawable.category_sidemenu,
                    R.drawable.category_daily,
                    R.drawable.category_processed};
    int image;

    public CategoryBtn(String category) {
        this.category = category;
        setImage();
    }

    public void setImage() {
        switch (category) {
            case "vegetable":
                image = images[0];
                break;
            case "fruit":
                image = images[1];
                break;
            case "bevarage":
                image = images[2];
                break;
            case "salad":
                image = images[3];
                break;
            case "sidemenu":
                image = images[4];
                break;
            case "daily":
                image = images[5];
                break;
            case "processed":
                image = images[6];
                break;
        }
    }

    public int getImage() {
        return image;
    }

    public String getCategory() {
        return category;
    }
}
