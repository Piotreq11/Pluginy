package me.piotreq.snowstats.objects;

public class User {

	private String name;
	private String UUID;
	private int dystans;
	private int wykopane_bloki;
	private float exp_stone;
	private float exp_drewno;
	private int wykopany_stone;
	private int wykopany_obs;
	private int wykopane_drewno;
	private int jedzenie;
	private int smierci;
	private int kille;
	private int zabite_moby;
	private int monety;
	private int lvl_stone;
	private int lvl_drewno;
	private int wymagany_stone;
	private int wymagany_drewno;
	private int postawione_bloki;
	private int do_stone;
	private int do_drewna;
	
	public User(int dystans, int wykopane_bloki, float exp_stone,float exp_drewno,int wykopany_stone,int wykopany_obs,int wykopane_drewno,int jedzenie,int smierci,int kille,int zabite_moby,String name,String UUID,int monety,int lvl_stone, int lvl_drewno,int postawione_bloki, int wymagany_stone, int wymagany_drewno, int do_stone, int do_drewna) {
		this.dystans = dystans;
		this.wykopane_bloki = wykopane_bloki;
		this.exp_stone = exp_stone;
		this.exp_drewno = exp_drewno;
		this.wykopany_stone = wykopany_stone;
		this.wykopany_obs = wykopany_obs;
		this.wykopane_drewno = wykopane_drewno;
		this.jedzenie = jedzenie;
		this.smierci = smierci;
		this.kille = kille;
		this.zabite_moby = zabite_moby;
		this.name = name;
		this.UUID = UUID;
		this.monety = monety;
		this.lvl_stone = lvl_stone;
		this.lvl_drewno = lvl_drewno;
		this.postawione_bloki = postawione_bloki;
		this.wymagany_stone = wymagany_stone;
		this.wymagany_drewno = wymagany_drewno;
		this.do_stone = do_stone;
		this.do_drewna = do_drewna;
	}
	public void setDostone(int w) {
		this.do_stone =w;
	}
	public int getDostone() {
		return do_stone;
	}
	public void setDodrewna(int w) {
		this.do_drewna = w;
	}
	public int getDodrewna() {
		return do_drewna;
	}
	public void setWymagany_drewno(int w) {
		this.wymagany_drewno = w;
	}
	public int getWymagany_drewno() {
		return wymagany_drewno;
	}
	public void setWymagany_stone(int w) {
		this.wymagany_stone = w;
	}
	public int getWymagany_stone() {
		return wymagany_stone;
	}
	public void setPostawione_bloki(int postawione_bloki) {
		this.postawione_bloki = postawione_bloki;
	}
	public int getPostawione_bloki() {
		return postawione_bloki;
	}
	public void setLvl_drewno(int lvl_drewno) {
		this.lvl_drewno = lvl_drewno;
	}
	public int getLvl_drewno() {
		return lvl_drewno;
	}
	public void setLvl_stone(int lvl_stone) {
		this.lvl_stone = lvl_stone;
	}
	public int getLvl_stone() {
		return lvl_stone;
	}
	public void setMonety(int monety) {
		this.monety = monety;
	}
	public int getMonety(){
		return monety;
	}
	public void setZabite_moby(int zabite_moby) {
		this.zabite_moby = zabite_moby;
	}
	public int getZabite_moby() {
		return zabite_moby;
	}
	public void setKille(int kille) {
		this.kille = kille;
	}
	public int getKille() {
		return kille;
	}
	public void setJedzenie(int jedzenie) {
		this.jedzenie = jedzenie;
	}
	public int getJedzenie() {
		return jedzenie;
	}
	public void setSmierci(int smierci) {
		this.smierci = smierci;
	}
	public int getSmierci() {
		return smierci;
	}
	public void setWykopany_obs(int wykopany_obs) {
		this.wykopany_obs = wykopany_obs;
	}
	public int getWykopany_obs() {
		return wykopany_obs;
	}
	public void setWykopany_stone(int wykopany_stone) {
		this.wykopany_stone = wykopany_stone;
	}
	public int getWykopany_stone() {
		return wykopany_stone;
	}
	public void setWykopane_drewno(int wykopane_drewno) {
		this.wykopane_drewno = wykopane_drewno;
	}
	public int getWykopane_drewno() {
		return wykopane_drewno;
	}
	public void setExp_drewno(double d) {
		this.exp_drewno = (float) d;
	}
	public float getExp_drewno() {
		return exp_drewno;
	}
	public void setExp_stone(double d) {
		this.exp_stone = (float) d;
	}
	public float getExp_stone() {
		return exp_stone;
	}
	public void setUUID(String uuid) {
		this.UUID = uuid;
	}
	public String getUUID() {
		return UUID;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void setDystans(int dystans) {
		this.dystans = dystans;
	}
	public int getDystans() {
		return dystans;
	}
	public void setWykopane_bloki(int wykopane_bloki) {
		this.wykopane_bloki = wykopane_bloki;
	}
	public int getWykopane_bloki() {
		return wykopane_bloki;
	}
}