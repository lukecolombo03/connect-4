package all.model;

import all.model.IModel;

/**
 * Represents command function objects whose only method is to apply their function to a model.
 */
public interface ICommand {
  void apply(IModel m);
}
