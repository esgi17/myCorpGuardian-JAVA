package pa.Models;

import org.json.JSONArray;
import org.json.JSONObject;

public abstract class ListDatas {

    public static Door[] getDoors() throws Exception {
        JSONObject empty = new JSONObject();

        // Recupere resultat requete
        String json = Api.callAPI("GET", "door/", empty);
        JSONObject datas = new JSONObject(json);
        JSONArray jArray = new JSONArray(datas.getString("datas"));
        Door[] doors = new Door[jArray.length()];

        for (int i = 0; i < jArray.length(); i++) {
            JSONObject door = jArray.getJSONObject( i );
            doors[i] = new Door();
            doors[i].setId(door.getString("id"));
            doors[i].setName(door.getString("name"));
            doors[i].setRef(door.getString("ref"));
            doors[i].setDeviceId(door.getString("device_id"));
        }
        return doors;
    }

    public static Group[] getGroups() throws Exception {
        JSONObject empty = new JSONObject();

        // Recupere resultat requete
        String json = Api.callAPI("GET", "group/", empty);
        JSONObject obj = new JSONObject(json);
        JSONArray jArray = new JSONArray(obj.getString("datas"));
        Group[] groups = new Group[jArray.length()];

        for (int i = 0; i < jArray.length(); i++) {
            JSONObject group = jArray.getJSONObject( i );
            groups[i] = new Group();
            groups[i].setId(group.getString("id"));
            groups[i].setName(group.getString("name"));

            JSONArray usersArray = new JSONArray(group.getString("users"));
            User[] users = createUsersArray(usersArray);
            groups[i].setUsers(users);
        }

        return groups;
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
}
