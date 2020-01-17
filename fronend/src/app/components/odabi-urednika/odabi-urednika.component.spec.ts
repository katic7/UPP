import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OdabiUrednikaComponent } from './odabi-urednika.component';

describe('OdabiUrednikaComponent', () => {
  let component: OdabiUrednikaComponent;
  let fixture: ComponentFixture<OdabiUrednikaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OdabiUrednikaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OdabiUrednikaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
