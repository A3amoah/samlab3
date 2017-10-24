package bbitga.strathmore.com.sqlitelaba;

/**
 * Created by nba on 19/10/2017.
 */

public class Unit {
    //private variables
    int _uid;
    String _uname;
    String _ucode;
    //empty constructor
    public Unit(){

    }
    public Unit(int _uid, String _uname, String _ucode){
        this._uid =_uid;
        this._uname =_uname;
        this._ucode=_ucode;

    }

    public Unit(String _uname, String _ucode){
        this._uname = _uname;
        this._ucode = _ucode;
    }

    public int get_uid() {
        return _uid;
    }

    public void set_uid(int _id) {
        this._uid = _uid;
    }

    public String get_uname() {
        return _uname;
    }

    public void set_uname(String _uname) {
        this._uname = _uname;
    }

    public String get_ucode() {
        return _ucode;
    }

    public void set_ucode(String _ucode) {
        this._ucode = _ucode;
    }
}

