public class RegisterList<T implements IForgeRegistryEntry> {
  private Map<ResourceLocation,T> data = new HashMap<ResourceLocation,T>();
  private bool registrationComplete = false;
  public T getByName(ResourceLocation name) {
    return data.get(name);
  }

  private void addObject(T obj) {
    if (registrationComplete)
      CustomLogHandler.send(new Message(sourceSet = new SourceBuilder()
                                        .add("RegisterList")
                                        .add("addObject")
                                        .build(),
                                        message = new MessageBuilder.ErrorBuilder()
                                        .cause("Registration Completed")
                                        .debugSuggestion("Ensure that RegisterList#addObject is called before RegistryEvent.Register<TYPE> is fired")
                                        .build()
                                       )
                            .meta(priority=Priority.FATAL,debugExtLog=ExtLog.REGISTRY,category=Category.REGISTRY + Category.LOCKED));
    else {
      CustomLogHandler.prepare(category = "RegisterList.Add", msg = obj.getResourceLocation().toString());
      
    }
  }
}
