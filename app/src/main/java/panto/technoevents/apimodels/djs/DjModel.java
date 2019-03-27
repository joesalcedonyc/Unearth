package panto.technoevents.apimodels.djs;

import android.os.Parcel;
import android.os.Parcelable;

public class DjModel implements Parcelable {
    private int id;
    private String name;
    private String country;
    private String image;

    protected DjModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        country = in.readString();
        image = in.readString();
    }

    public static final Creator<DjModel> CREATOR = new Creator<DjModel>() {
        @Override
        public DjModel createFromParcel(Parcel in) {
            return new DjModel(in);
        }

        @Override
        public DjModel[] newArray(int size) {
            return new DjModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getImage() {
        return image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(id);
        dest.writeString(country);
        dest.writeString(image);
    }
}
