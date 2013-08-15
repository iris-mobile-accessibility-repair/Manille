package be.quentinloos.manille.util;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import be.quentinloos.manille.core.Manille;
import be.quentinloos.manille.core.ManilleFree;
import be.quentinloos.manille.core.ManilleScore;
import be.quentinloos.manille.core.ManilleTurns;

/**
 * A parcelable implementation of Manille
 *
 * @author Quentin Loos <contact@quentinloos.be>
 */
public class ManilleParcelable implements Parcelable {

    Manille manille;

    public ManilleParcelable(Manille manille) {
        this.manille = manille;
    }

    public ManilleParcelable(Parcel source) {
        int type = source.readInt();
        if (type == 0)
            manille = new ManilleFree();
        else if (type == 1)
            this.manille = new ManilleScore(source.readInt());
        else if (type == 2)
            this.manille = new ManilleTurns(source.readInt());
        this.readFromParcel(source);
    }

    public Manille getManille() {
        return manille;
    }

    private void readFromParcel(Parcel source) {
        int[] score = new int[2];
        source.readIntArray(score);
        manille.setScore(score);
        ArrayList<int[]> turns = new ArrayList<int[]>();
        source.readList(turns, null);
        manille.setTurns(turns);
        manille.setMult(source.readInt());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // 0. ManilleFree
        // 1. ManilleScore
        // 2. ManilleTurns
        if (manille instanceof ManilleFree)
            dest.writeInt(0);
        else if (manille instanceof ManilleScore) {
            dest.writeInt(1);
            dest.writeInt(((ManilleScore) manille).getEnding());
        }
        else if (manille instanceof ManilleTurns) {
            dest.writeInt(2);
            dest.writeInt(((ManilleTurns) manille).getEnding());
        }
        dest.writeIntArray(manille.getScore());
        dest.writeList(manille.getTurns());
        dest.writeInt(manille.getMult());
    }

    public static final Parcelable.Creator<ManilleParcelable> CREATOR = new Parcelable.Creator<ManilleParcelable>() {

        @Override
        public ManilleParcelable createFromParcel(Parcel source) {

            return new ManilleParcelable(source);
        }

        @Override
        public ManilleParcelable[] newArray(int size) {

            return new ManilleParcelable[size];
        }
    };
}
