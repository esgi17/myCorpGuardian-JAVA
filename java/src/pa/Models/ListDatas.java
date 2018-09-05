package pa.Models;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.json.JSONArray;
import org.json.JSONObject;

public abstract class ListDatas {

    public static Door[] getDoors() throws Exception {
        JSONObject empty = new JSONObject();

        // Recupere resultat requete
        String json = Api.callAPI("GET", "door/", empty);
        if (!json.equalsIgnoreCase("")) {
            JSONObject datas = new JSONObject( json );
            JSONArray jArray = new JSONArray( datas.getString( "datas" ) );
            Door[] doors = new Door[jArray.length()];

            for (int i = 0; i < jArray.length(); i++) {
                JSONObject door = jArray.getJSONObject( i );
                doors[i] = new Door();
                doors[i].setId( door.getString( "id" ) );
                doors[i].setDeviceId( door.getString( "device_id" ) );
            }
            return doors;
        }
        else{
            Door[] doorEmpty = new Door[0];
            return  doorEmpty;
        }
    }

    public static Group[] getGroups() throws Exception {
        JSONObject empty = new JSONObject();
        Group[] groups;

        // Recupere resultat requete
        String json = Api.callAPI("GET", "group/", empty);
        if (!json.equalsIgnoreCase("")) {
            JSONObject obj = new JSONObject( json );
            JSONArray jArray = new JSONArray( obj.getString( "datas" ) );
            groups = new Group[jArray.length()];

            for (int i = 0; i < jArray.length(); i++) {
                JSONObject group = jArray.getJSONObject( i );
                groups[i] = new Group();
                groups[i].setId( group.getString( "id" ) );
                groups[i].setName( group.getString( "name" ) );

                JSONArray usersArray = new JSONArray( group.getString( "users" ) );
                User[] users = createUsersArray( usersArray );
                groups[i].setUsers( users );
            }

            return groups;
        }
        else{
            Group[] groupEmpty = new Group[0];
            return  groupEmpty;
        }
    }

    public static User[] getUsers() throws Exception {
        JSONObject empty = new JSONObject();
        User[] userEmpty = new User[0];
        // Recupere resultat requete
        String json = Api.callAPI( "GET", "user/", empty );
        if (!json.equalsIgnoreCase( "" )) {
            JSONObject obj = new JSONObject( json );
            JSONArray jArray = new JSONArray( obj.getString( "datas" ) );
            User[] users = createUsersArray( jArray );
            return users;
        } else {
            return userEmpty;
        }
    }

    public static Pass[] getPass() throws Exception {
        JSONObject empty = new JSONObject();

        // Recupere resultat requete
        String json = Api.callAPI("GET", "pass/", empty);
        if (!json.equalsIgnoreCase("")) {
            JSONObject obj = new JSONObject( json );
            JSONArray jArray = new JSONArray( obj.getString( "datas" ) );
            Pass[] passes = new Pass[jArray.length()];

            for (int i = 0; i < jArray.length(); i++) {
                JSONObject pass = jArray.getJSONObject( i );
                passes[i] = new Pass();
                passes[i].setId( pass.getString( "id" ) );
                passes[i].setIdDevice( pass.getString( "device_id" ) );
                passes[i].setIdUser( pass.getString( "user_id" ) );
            }
            return passes;
        }
        Pass[] passEmpty = new Pass[0];
        return  passEmpty;
    }


    public static User[] createUsersArray(JSONArray usersArray) throws Exception {
        User[] users = new User[usersArray.length()];
        for (int j = 0; j < usersArray.length(); j++) {
            JSONObject user = usersArray.getJSONObject( j );
            users[j] = new User();
            users[j].setId( user.getString( "id" ) );
            users[j].setFirstname( user.getString( "firstname" ) );
            users[j].setLastname( user.getString( "lastname" ) );
            users[j].setJob( user.getString( "job" ) );
            users[j].setIdGroup( user.getString( "group_id" ) );
        }
        return users;
    }

    public static Schedule[] getSchedule() throws Exception {
        JSONObject empty = new JSONObject();

        // Recupere resultat requete
        String json = Api.callAPI("GET", "schedule/", empty);
        if (!json.equalsIgnoreCase("")) {
            JSONObject datas = new JSONObject(json);
            JSONArray jArray = new JSONArray(datas.getString("datas"));
            Schedule[] schedules = new Schedule[jArray.length()];

            for (int i = 0; i < jArray.length(); i++) {
                JSONObject schedule = jArray.getJSONObject( i );
                schedules[i] = new Schedule();
                schedules[i].setId(schedule.getString("id"));
                schedules[i].setDay(schedule.getString("day"));
                schedules[i].setDoorId(schedule.getString("door_id"));
                schedules[i].setGroupId(schedule.getString("group_id"));
                schedules[i].setHStart(schedule.getString("h_start"));
                schedules[i].setHStop(schedule.getString("h_stop"));

            }
            return schedules;
        }
        else{
            Schedule[] scheduleEmpty = new Schedule[0];
        return  scheduleEmpty;
    }

    }

    public static Event[] getEvent() throws Exception {
        JSONObject empty = new JSONObject();

        // Recupere resultat requete
        String json = Api.callAPI("GET", "event/", empty);
        if (!json.equalsIgnoreCase("")) {
        JSONObject datas = new JSONObject(json);
        JSONArray jArray = new JSONArray(datas.getString("datas"));
        Event[] events = new Event[jArray.length()];

        for (int i = 0; i < jArray.length(); i++) {
            JSONObject door = jArray.getJSONObject( i );
            events[i] = new Event();
            events[i].setId(door.getString("id"));
            events[i].setTitle(door.getString("title"));
            events[i].setDate(door.getString("date"));
            events[i].setPassId(door.getString("pass_id"));
            events[i].setDeviceId(door.getString("device_id"));
        }
        return events;
    }
    else{
            Event[] eventEmpty = new Event[0];
        return  eventEmpty;
        }
    }

    public static Device[] getDevices() throws Exception {
        JSONObject empty = new JSONObject();

        // Recupere resultat requete
        String json = Api.callAPI("GET", "device/", empty);
        if (!json.equalsIgnoreCase("")) {
            JSONObject datas = new JSONObject( json );
            JSONArray jArray = new JSONArray( datas.getString( "datas" ) );
            Device[] devices = new Device[jArray.length()];

            for (int i = 0; i < jArray.length(); i++) {
                JSONObject device = jArray.getJSONObject( i );
                devices[i] = new Device();
                devices[i].setId( device.getString( "id" ) );
                devices[i].setName( device.getString( "name" ) );
                devices[i].setRef( device.getString( "ref" ) );
                devices[i].setDeviceTypeId( device.getString( "device_type_id" ) );
            }
            return devices;
        }
        else{
            Device[] deviceEmpty = new Device[0];
            return  deviceEmpty;
        }
    }

    public static Device getDevice(String id) throws Exception {
        JSONObject empty = new JSONObject();
        Device device = new Device();
        String json = Api.callAPI("GET", "device/" + id, empty);
        if (!json.equalsIgnoreCase("")) {
            JSONObject datas = new JSONObject( json );
            JSONArray jArray = new JSONArray( datas.getString( "datas" ) );
            JSONObject dev = jArray.getJSONObject(0);
            device.setId(dev.getString("id"));
            device.setName(dev.getString("name"));
            device.setRef(dev.getString("ref"));
            device.setDeviceTypeId(dev.getString("device_type_id"));
        }
        return device;
    }

}
