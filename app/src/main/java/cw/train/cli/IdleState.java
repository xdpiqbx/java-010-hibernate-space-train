package cw.train.cli;

public class IdleState extends CliState {
    public IdleState(CliFSM cliFSM) {
        super(cliFSM);
    }

    @Override
    public void unknownCommand(String cmd) {
        System.out.print("UnknowncCommand: " + cmd);
    }

    @Override
    public void newTickedRequested() {
        fsm.setState(new AddTicketState(fsm));
        super.newTickedRequested();
    }

    @Override
    public void planetStatsRequested() {
        fsm.setState(new PlanetStatsState(fsm));
        super.planetStatsRequested();
    }
}
