package pa.plugins;


import java.io.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarFile;

public class PluginLoader {

    /**
     * Liste des plugins installés
     */
    private ArrayList<String> installedPlugins = new ArrayList<String>();

    /**
     * Liste des plugins actifs
     */
    private ArrayList<String> activePlugins = new ArrayList<String>();

    /**
     * ??
     * Listes des chemins de plugins à installer
     */
    private ArrayList<String> uninstalledPluginsPath = new ArrayList<String>();

    /**
     * Chemin vers le dossier des plugins installés
     */
    private String plugin_dir_path = "src/pa/plugins/plugins_installed/";

    /**
     * Chemin du fichier contenant la liste des plugins
     */
    private String plugin_list_path = plugin_dir_path + "plugin-list.txt";

    /**
     * Fichier contenant la liste des plugins installés
     */
    private File pluginList = new File(plugin_list_path);

    /**
     * Liste de plugins chargés
     */
    private static Plugins[] plugins;
    /**
     * Liste des classe chargés
     */
    private static ArrayList<Class> pluginsClass = new ArrayList<Class>();

    public ArrayList<String> getInstalledPlugins() {
        return installedPlugins;
    }

    public ArrayList<Class> getPluginsClass() {
        return this.pluginsClass;
    }

    public void setInstalledPlugins(ArrayList<String> installedPlugins) {
        this.installedPlugins = installedPlugins;
    }

    public ArrayList<String> getActivePlugins() {
        return activePlugins;
    }

    public void setActivePlugins(ArrayList<String> activePlugins) {
        this.activePlugins = activePlugins;
    }

    public String getPlugin_dir_path() {
        return plugin_dir_path;
    }

    public void setPlugin_dir_path(String plugin_dir_path) {
        this.plugin_dir_path = plugin_dir_path;
    }

    public String getPlugin_list_path() {
        return plugin_list_path;
    }

    public void setPlugin_list_path(String plugin_list_path) {
        this.plugin_list_path = plugin_list_path;
    }

    public File getPluginList() {
        return pluginList;
    }

    public void setPluginList(File pluginList) {
        this.pluginList = pluginList;
    }

    public PluginLoader() throws IOException {
        this.loadActivePlugins();

        if( plugins != null ) {
            for( int i = 0 ; i < plugins.length ; i++ ) {
                plugins[i].plug();
            }
        }
    }

    private void loadActivePlugins() throws IOException{
        try {

            BufferedReader file = new BufferedReader(new FileReader(plugin_list_path));
            ArrayList<String> tmpPlugins = new ArrayList<String>();
            String line;

            while( (line = file.readLine()) != null ) {
                // Récupération du nom du plugin
                String[] array = line.split("-");
                array = array[1].split(">");
                String name = array[0];
                array = array[1].split(":");
                String path = array[0];
                String isActive = array[1];
                // Si le plugin est actif
                if( isActive.equals("1") ) {
                    // Ajout du nom du plugin dans le tableau temporaire
                    tmpPlugins.add(plugin_dir_path + path);
                }
                // Ajout du nom du plugin dans le tableau des plugins installés
                this.installedPlugins.add(name);
            }
            file.close();
            if( tmpPlugins.size() < 1 ) {
                return ;
            }
            this.plugins = loadPlugins(tmpPlugins, 1);


        } catch ( FileNotFoundException e ) {
            e.printStackTrace();
            // Fichier non trouvé => Aucun plugin active
            //                    => Création du fichier
            //                    => return null
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Plugins[] loadPlugins(ArrayList<String> pluginsPathList, int type) throws Exception {
        if( pluginsPathList == null ) {
            return null;
        }
        this.initializeLoader(pluginsPathList, type);
        Plugins[] tmpPlugins = new Plugins[pluginsPathList.size()];
        for( int i = 0 ; i < tmpPlugins.length ; i++) {
            tmpPlugins[i] = (Plugins)(this.pluginsClass.get(i)).newInstance();
        }

        return tmpPlugins;
    }

    private void initializeLoader(ArrayList<String> pluginsPathList, int type) throws Exception {
        if( pluginsPathList == null || pluginsPathList.size() == 0 ) {
            throw new Exception("No files selected");
        }

        File[] f = new File[pluginsPathList.size()];
        URLClassLoader loader;
        String tmp = "";
        Enumeration enumeration;

        Class tmpClass = null;

        for(int i = 0 ; i < pluginsPathList.size(); i++) {
            f[i] = new File( pluginsPathList.get(i) );
            if( !f[i].exists()) {
                break;
            }

            URL u = f[i].toURL();
            loader = new URLClassLoader(new URL[] {u});

            JarFile jar = new JarFile(f[i].getAbsolutePath());

            enumeration = jar.entries();

            while(enumeration.hasMoreElements()) {
                tmp = enumeration.nextElement().toString();
                if( tmp.length() > 6 && tmp.substring(tmp.length() - 6).compareTo(".class") == 0 ) {
                    tmp = tmp.substring(0, tmp.length()-6);
                    tmp = tmp.replaceAll("/",".");

                    tmpClass = Class.forName(tmp, true, loader);
                    if( type == 0 )
                        this.savePlugin(f[i].getAbsolutePath(), tmpClass.getName());
                    if( tmpClass == null ) {
                        break;
                    }
                    this.pluginsClass.add(tmpClass);
                }
            }
            jar.close();

        }
    }


    public void savePlugin(String path,String name) {

        try (FileChannel input = new FileInputStream(path).getChannel()) {
            FileChannel output = new FileOutputStream(plugin_dir_path + name + ".jar").getChannel();
            input.transferTo(0, input.size(), output);

            PrintWriter file = new PrintWriter(new BufferedWriter(new FileWriter(pluginList)));
            file.println("-" + name + ">" + name + ".jar:1");
            file.close();

        } catch( IOException e) {
            e.printStackTrace();

        }
    }





    /*
    static final String plugin_dir_path = "src/pa/plugins/plugins_installed/";
    static final String plugin_list = plugin_dir_path + "plugin-list.txt";


    private ObservableList<String> pluginsPath = FXCollections.observableArrayList();;
    private static ArrayList<Class> plugins;

    public PluginLoader() {
        this.plugins = new ArrayList<>();
    }

    public void savePlugin(String path,String name) {

        try (FileChannel input = new FileInputStream(path).getChannel()) {
            PrintWriter writer = new PrintWriter(plugin_dir_path + name + ".jar", "UTF-8");
            writer.close();
            FileChannel output = new FileOutputStream(plugin_dir_path + name + ".jar").getChannel();
            input.transferTo(0, input.size(), output);

            PrintWriter file = new PrintWriter(new BufferedWriter(new FileWriter(plugin_list)));
            file.println("- " + name + " > " + name + ".jar : 1");
            file.close();

        } catch( IOException e) {
            e.printStackTrace();

        }
    }

    public void setPluginsPath(ObservableList<String> pluginsPath) {
        this.pluginsPath.addAll(pluginsPath);
    }

    public void addPluginsPath(String str) {
        this.pluginsPath.add(str);
    }


    public Plugins[] loadPlugins() throws Exception {
        this.initializeLoader();

        Plugins[] tmpPlugins = new Plugins[this.plugins.size()];
        System.out.println(plugins);
        for( int i = 0 ; i < tmpPlugins.length ; i++) {
            tmpPlugins[i] = (Plugins)(this.plugins.get(i)).newInstance();
        }

        return tmpPlugins;
    }


    public void initializeLoader() throws Exception {
        if( this.pluginsPath == null ||this.pluginsPath.size() == 0 ) {
            throw new Exception("No files selected");
        }

        if( this.plugins.size() != 0 ) {
            return ;
        }

        File[] f = new File[this.pluginsPath.size()];
        URLClassLoader loader;
        String tmp = "";
        Enumeration enumeration;

        Class tmpClass = null;

        for(int i = 0 ; i < this.pluginsPath.size(); i++) {
            f[i] = new File( this.pluginsPath.get(i) );
            if( !f[i].exists()) {
                break;
            }

            URL u = f[i].toURL();
            loader = new URLClassLoader(new URL[] {u});

            JarFile jar = new JarFile(f[i].getAbsolutePath());

            enumeration = jar.entries();

            while(enumeration.hasMoreElements()) {
                tmp = enumeration.nextElement().toString();
                if( tmp.length() > 6 && tmp.substring(tmp.length() - 6).compareTo(".class") == 0 ) {
                    tmp = tmp.substring(0, tmp.length()-6);
                    tmp = tmp.replaceAll("/",".");

                    tmpClass = Class.forName(tmp, true, loader);
                    this.savePlugin(f[i].getAbsolutePath(), tmpClass.getName());
                    this.plugins.add(tmpClass);
                }
            }

        }
    }

    public boolean initializePlugins() throws Exception {
        ArrayList<String> activePlugins = getActivePlugins();
        Plugins[] plugins;
        pluginsPath.removeAll();

        for( int i = 0 ; i < activePlugins.size() ; i++ ) {
            try {
                Class.forName(activePlugins.get(i));
                System.out.println(activePlugins.get(i));
            } catch (ClassNotFoundException e) {
                File f = new File(plugin_dir_path + activePlugins.get(i) + ".jar");
                pluginsPath.add(f.getAbsolutePath());
            }
        }

        if( (plugins = loadPlugins()) != null ) {
            plugins[0].getName();
            return true;
        }
        return false;
    }

    private ArrayList<String> getActivePlugins() throws IOException {
        ArrayList<String> plugins = new ArrayList<String>();
        String line;
        Integer i = 0;
        BufferedReader file = new BufferedReader(new FileReader(plugin_list));

        while ( (line = file.readLine()) != null ) {
            System.out.println(line);
            String[] array = line.split("-");
            array = array[1].split(">");
            String name = array[0];
            plugins.add(name);
            i++;
        }
        file.close();

        return plugins;
    }
    */
}
